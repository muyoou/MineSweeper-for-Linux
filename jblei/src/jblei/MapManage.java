package jblei;

import java.util.Random;

/*
 * MapManage 管理扫雷地图
 * 
 */

public class MapManage {
	private final int wi;
	private final int he;
	private int[][] newMap;
	
	/*
	 * MapManage 初始化地图
	 * @param wi，he 地图宽和高
	 */
	
	public MapManage(int wi,int he) {
		this.wi=wi;
		this.he=he;
		newMap=new int[wi][he];
	} 
	
	/*
	 * create 创建地图
	 * @param num 地雷数量
	 */
	
	public void create(int num) {
		Random random =new Random();
		Random random2 = new Random();
		for(int i=0;i<num;i++) {
			newMap[random.nextInt(wi)][random2.nextInt(he)]=9;
		}
		for(int i=0;i<wi;i++)
			for(int j=0;j<he;j++)
				if(newMap[i][j]!=9)
					newMap[i][j]=mineNum(i, j);
	}
	
	/*
	 * getMap 获取地图
	 * @return 地图数组
	 */
	
	public int[][] getMap(){
		return newMap;
	}
	
	/*
	 * getNum 获取指定坐标的地图数字
	 * @param x,y 指定的坐标
	 * @return 返回数字
	 */
	
	public  int getNum(int x,int y) {
		return newMap[x][y];
	}
	
	/*
	 * mineNum 获取指定坐标周围的地雷数量
	 * @param x,y 指定的坐标
	 */
	
	private int mineNum(int x,int y) {
		int num=0;
		for(int i=-1;i<2;i++) 
			for(int j=-1;j<2;j++)
				num+=isMine(x+i, y+j)?1:0;
		return num;
	}
	
	/*
	 * getWidth 返回宽度
	 */
	
	public int getWidth() {
		return wi;
	}
	
	/*
	 * getHeight 返回高度
	 */
	public int getHeight() {
		return he;
	}
	
	/*
	 * haveNull 返回指定坐标周围是否有空块
	 */
	
	public boolean haveNull(int x,int y) {
		if(newMap[x][y]==9) return false;
		for(int i=-1;i<2;i++) 
			for(int j=-1;j<2;j++)
				if(isNull(x+i, y+j)) return true;
		return false;
	}
	
	/*
	 * isMine 指定坐标是否是雷
	 */
	
	public boolean isMine(int x,int y) {
		if(x>=0&&y>=0&&x<wi&&y<he&&newMap[x][y]==9) return true;
		else return false;
	}
	
	/*
	 * isNull 指定坐标是否是空块
	 */
	
	public boolean isNull(int x,int y) {
		if(x>=0&&y>=0&&x<wi&&y<he&&newMap[x][y]==0) return true;
		else return false;
	}

}
