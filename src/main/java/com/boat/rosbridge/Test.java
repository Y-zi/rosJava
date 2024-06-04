package com.boat.rosbridge;

import com.boat.rosbridge.message.msg.map_msgs.PointListEntry;
import com.boat.rosbridge.message.msg.map_msgs.SetTargetGoal;
import com.boat.rosbridge.message.msg.std_msgs.Int16;
import com.boat.rosbridge.message.msg.std_msgs.Int32;
import com.boat.rosbridge.message.srv.map_msgs.ListNaviPointsResponse;
import com.boat.rosbridge.message.srv.map_msgs.PublishMapRequest;
import com.boat.rosbridge.message.srv.std_srvs.Empty;
import com.boat.rosbridge.message.srv.std_srvs.TriggerResponse;
import com.boat.rosbridge.utils.ServiceNames;
import com.boat.rosbridge.utils.TopicNames;
import com.boat.support.slam.entity.floors.Floors;
import com.boat.support.slam.entity.floors.MapList;
import com.boat.support.slam.entity.floors.Maps;
import com.boat.support.slam.entity.floors.Points;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jilk.ros.MessageHandler;
import com.jilk.ros.ROSClient;
import com.jilk.ros.Service;
import com.jilk.ros.Topic;
import com.jilk.ros.rosbridge.ROSBridgeClient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static ROSBridgeClient client;
    //这里是为了过滤导航点中 高速路的无效方向导航点
    public static String CANCLEPOINTNAME = "c_j_x_line_point";


    public static void main(String[] args) {

        client = new ROSBridgeClient("ws://10.7.5.88:9090");

        client.connect(new ROSClient.ConnectionStatusListener() {
            @Override
            public void onConnect() {
                System.out.println("ROSBridgeClient onConnect: ws 成功");
                initData();
            }

            @Override
            public void onDisconnect(boolean normal, String reason, int code) {
                // -1 连接不上， 1000 手机主动dis断开  1006 服务端或手机意外网络断开

                System.out.println("ROSBridgeClient onDisconnect: code " + code);

            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                System.out.println("ROSBridgeClient 连接失败");
            }
        });


    }

    //获取数据
    private static void initData() {

        //当前初始化状态订阅（地图是否加载成功）
        Topic<Int16> initStatusTopic = new Topic<>(TopicNames.InitMsg, Int16.class, client);
        initStatusTopic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 message) {
                if (message.data == 11) {
                    System.out.println("onMessage: 初始化失败");
                } else if (message.data == 12) {
                    System.out.println("onMessage: 初始化成功");
                }
            }
        });
        //initStatusTopic.unsubscribe();//解除订阅


        // 电池电量订阅
        Topic<Int16> powerTopic = new Topic<>(TopicNames.Battry, Int16.class, client);
        powerTopic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 message) {
                System.out.println("电量: " + message.data);
            }
        });

        // 充电状态订阅
        Topic<Int16> chageMsgTopic = new Topic<>(TopicNames.ChageMsg, Int16.class, client);
        chageMsgTopic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 message) {
//                41 没有充电           // 42 充电中(弃用)43 充电完成(弃用)
//                44 充电失败
//                45 自动充电中 46 手动充电中
//                47 自动充电完成 48 手动充电完成

                if (message.data == 45 || message.data == 46 ||
                        message.data == 47 || message.data == 48) {
                    System.out.println("充电中");
                } else {
                    System.out.println("未充电");
                }


            }
        });

        // 急停状态订阅
        Topic<Int16> emeStatusTopic = new Topic<>(TopicNames.EMEMsg, Int16.class, client);
        emeStatusTopic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 message) {
//                31 机器人处于急停状态！机器人电机锁住不动
//                32 机器人急停状态取消
                int data = message.data;

                if (data == 31) {
                    System.out.println("急停按下");
                }
                if (data == 32) {
                    System.out.println("急停释放");
                }
            }

        });


        //当前地图模式订阅
        Topic<Int16> mapStatusTopic = new Topic<>(TopicNames.MapStatus, Int16.class, client);
        mapStatusTopic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 int16) {
                //0导航模式 1建图模式 2扩展地图模式
                int data = int16.getData();
                System.out.println("topic 地图模式: " + data);
            }
        });


        //机器当前地图的集合
        List<Maps> currentMapList = new ArrayList<Maps>();
        long currentMapId = 0;
        //当前地图的导航点集合
        List<Points> currentMapPosintList = new ArrayList<Points>();



        //当机器建图时
        List<Points> buildMapLocalAllPoint = new LinkedList<>();
        List<Points> buildMapLocalNaviPoints = new LinkedList<>();
        List<Points> buildMapLocalSystemPoint = new LinkedList<>();
        //当地图数据是否发生变化订阅
        Topic<Int32> mapUpdateTopic = new Topic<>(TopicNames.WallUpdateMsg, Int32.class, client);
        mapUpdateTopic.subscribe(new MessageHandler<Int32>() {
            @Override
            public void onMessage(Int32 message) {
                //当地图数据发生变化就会触发这里



                //读取地图列表
                Service<Empty, TriggerResponse> mapsService = new Service<Empty, TriggerResponse>
                        (ServiceNames.GetMaps, Empty.class, TriggerResponse.class, client);
                mapsService.callWithHandler(new Empty(), new MessageHandler<TriggerResponse>() {
                    @Override
                    public void onMessage(TriggerResponse message) {
                        if (message.getSuccess()) {
                            if (message.getMessage().isEmpty()) return;
                            MapList localMapList = new MapList();
                            try {
                                localMapList = new Gson().fromJson(message.getMessage(), new TypeToken<MapList>() {
                                }.getType());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            for (Floors floors : localMapList.getFloors()) {
                                if (floors.getFloorId() == localMapList.getDefaultFloor()) {
                                    for (Maps maps : floors.getMaps()) {
                                        if (floors.getDefaultmap() == maps.getMapId()) {

                                            //填入数据
                                            currentMapList .addAll(floors.getMaps());
//                                            currentMapId =floors.getDefaultmap();
                                            for (Points pointListEntry : maps.getPoints()) {
                                                if (pointListEntry.getPointName().equals(CANCLEPOINTNAME))
                                                    continue;
                                                currentMapPosintList.add(pointListEntry);
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                });


                //获取建图时候的临时地图信息,当机器处于建图状态时候，可以从这里获取导航点数据
                Service<Empty, ListNaviPointsResponse> buildPoints = new Service<Empty, ListNaviPointsResponse>
                        (ServiceNames.NaviPoints, Empty.class, ListNaviPointsResponse.class, client);
                buildPoints.callWithHandler(new Empty(), new MessageHandler<ListNaviPointsResponse>() {
                    @Override
                    public void onMessage(ListNaviPointsResponse response) {

                        buildMapLocalAllPoint.clear();
                        buildMapLocalNaviPoints.clear();
                        buildMapLocalSystemPoint.clear();

                        List<PointListEntry> naviPoints = response.getListNaviPoints();
                        List<PointListEntry> systemPoints = response.getListSystemPoints();
                        if (naviPoints != null)
                            for (PointListEntry entry : naviPoints) {
                                if (entry.getPointName().equals(CANCLEPOINTNAME))
                                    continue;
                                Points points = new Points();
                                points.setPointId(Long.parseLong(entry.getPointId()));
                                points.setPointName(entry.getPointName());
                                points.setPositionX(entry.getX());
                                points.setPositionY(entry.getY());
                                points.setPositionYaw(entry.getZ());
                                buildMapLocalAllPoint.add(points);
                                buildMapLocalNaviPoints.add(points);
                            }
                        if (systemPoints != null)
                            for (PointListEntry entry : systemPoints) {
                                if (entry.getPointName().equals(CANCLEPOINTNAME))
                                    continue;
                                Points points = new Points();
                                points.setPointId(Long.parseLong(entry.getPointId()));
                                points.setPointName(entry.getPointName());
                                points.setPositionX(entry.getX());
                                points.setPositionY(entry.getY());
                                points.setPositionYaw(entry.getZ());
                                buildMapLocalAllPoint.add(points);
                                buildMapLocalSystemPoint.add(points);
                            }

                    }

                });

            }
        });

        //导航结果回调
        Topic<Int16> navStatusTpic = new Topic<>(TopicNames.NavigationMsg, Int16.class, client);
        navStatusTpic.subscribe(new MessageHandler<Int16>() {
            @Override
            public void onMessage(Int16 message) {
                int s = message.getData();
                System.out.println("topic 导航状态: status = " + s);
//                  1,       // 开始导航
//                  2,       // 导航到目的点成功
//                  3,       // 导航到导航点进行各种尝试后失败，导航停止
//                  4,       // 局部路径规划规划失败，并且超时，进入恢复模式
//                  5,       // 导航进入地图CLEANING模式，原先值为12
//                  6,       // 找不到导航点
//                  7,       // 停止导航 下发stop命令后上报
//                  8,       // 全局路径规划失败，并且进入超时,碰到障碍物，提醒请让一让
//                  51:      // 机器人充电过程中，导航到充电桩过程中导航失败，导致充电失败！!";
//                  52:      // 机器人没有设置充电桩！!";
//                  81:      // 机器人导航验证成功！
//                  82:      // 机器人导航验证失败！
//                  83:      // 机器人导航取消！
//                  84:      // 机器人位置已丢失，请重新定位才能导航！

            }
        });

    }

    /**
     * 开启导航
     * @param pointName 传入导航点名称
     * */
    private void goNavito(String pointName) {

        System.out.println("导航任务: " + pointName);
        Topic<SetTargetGoal> setTargetGoalPublish = new Topic<SetTargetGoal>(TopicNames.NaviToPointName, SetTargetGoal.class, client);
        //注册发布消息类型
        setTargetGoalPublish.advertise();

        SetTargetGoal setTargetGoal = new SetTargetGoal();
        setTargetGoal.setGoalName(pointName);

        setTargetGoalPublish.publish(setTargetGoal);

        //解除注册发布类型
        setTargetGoalPublish.unadvertise();
    }

    //停止导航/停止运动
    private static void startNavi(){
        Topic<Int16> stopNaviPublish = new Topic<Int16>(TopicNames.NaviStop, Int16.class, client);
        stopNaviPublish.advertise();
        Int16 int16 = new Int16();
        int16.setData((short) 5);
        stopNaviPublish.publish(int16);
        stopNaviPublish.unadvertise();
    }

    //自动充电
    private static void changerMap(){
        Topic<Int16> goChargePublish = new Topic<Int16>(TopicNames.GoCharge, Int16.class, client);
        goChargePublish.advertise();
        Int16 changeint16 = new Int16();
        changeint16.setData((short) 1);
        goChargePublish.publish(changeint16);
        goChargePublish.unadvertise();
    }



    //切换地图
    //加载完成后 initStatusTopic 消息就会触发
    private void loadMap(String mapId) {

        System.out.println("加载地图中...");

        Service<PublishMapRequest, Empty> changeMap = new Service<PublishMapRequest, Empty>
                (ServiceNames.ChangeMap, PublishMapRequest.class, Empty.class, client);
        PublishMapRequest request = new PublishMapRequest();
        request.setMapId(mapId);
        changeMap.callWithHandler(request, new MessageHandler<Empty>() {
            @Override
            public void onMessage(Empty message) {
                System.out.println("加载消息请求成功");
            }

        });
    }


}
