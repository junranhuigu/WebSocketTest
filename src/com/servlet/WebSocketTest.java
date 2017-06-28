package com.servlet;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/boomShakalaka")
public class WebSocketTest {
	
	@OnMessage
	public void onMessage(String message, Session session) throws Exception{
		System.out.println("Recieve message : " + message);
		
		session.getBasicRemote().sendText(message + "市网警已检测到你发送了非法信息 , 请速至最近的派出所投案自首！");
		System.out.println("向恐怖分子发送通告");
		
		int messageCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> buildings = attackBuildings(message);
		int len = (int) (Math.random() * buildings.size());
		while(messageCount < len){
			Thread.sleep(500);
			int r = (int) (Math.random() * buildings.size());
			session.getBasicRemote().sendText("警告：" + sdf.format(new Date()) + " " + buildings.remove(r) + " 被袭击， 破坏程度：" + (int)(Math.random() * 100) + "%，伤亡人数：" + (int)(Math.random() * 1000) + "，剩余目标个数 : " + (len - (messageCount ++)));
			System.out.println("抗议！" + messageCount);
		}
		session.getBasicRemote().sendText(message + "市已被你成功袭击");
		System.out.println("勿谓言之不预也");
	}
	
	@OnOpen
	public void onOpen(){
		System.out.println("client connected");
	}
	
	@OnClose
	public void onClose(){
		System.out.println("connect closed");
	}
	
	private List<String> attackBuildings(String city){
		List<String> buildings = new ArrayList<>();
		buildings.add("警察局");
		buildings.add(city + "市政府办公楼");
		buildings.add(city + "市第一人民中心医院");
		buildings.add(city + "市中医院");
		buildings.add("消防局");
		buildings.add(city + "大学");
		buildings.add(city + "市粮食局");
		buildings.add(city + "市武警总部");
		buildings.add(city + "市火车站");
		buildings.add(city + "市汽车站");
		buildings.add(city + "市" + (int)(Math.random() * 10) + "号加油站");
		buildings.add("电站");
		buildings.add("炼油厂");
		buildings.add("油库");
		buildings.add("水库");
		buildings.add("车站");
		buildings.add((int)(Math.random() * 10) + "号码头");
		buildings.add(city + "机场");
		buildings.add("桥梁");
		buildings.add("通信广播");
		buildings.add(city + "电视台");
		buildings.add(city + "机械制造厂");
		buildings.add(city + "电子设备制造厂");
		buildings.add(city + "化工厂");
		return buildings;
	}
	
}
