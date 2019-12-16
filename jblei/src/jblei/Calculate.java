package jblei;

/*
 * Calculate : 用于管理遮盖层
 * 
 */

public class Calculate {
	private int[][] newCover;
	private final int wi,he;
	MapManage manage;
	
	/*
	 * Calculate : 使用MapManage初始化
	 */
	
	public Calculate(MapManage manage) {
		this.manage=manage;
		this.wi=manage.getWidth();
		this.he=manage.getHeight();
		newCover=new int[wi][he];
	}
	
	/*
	 * OpenOne : 打开一个坐标的方块可能发生的事件
	 * @param x,y 要打开的坐标
	 */
	
	public boolean OpenOne(int x,int y) {
		if(!manage.haveNull(x, y)) {
			if(manage.isMine(x, y)) {
				return false;
			}
			else {
				setOpen(x, y);
				return true;
			}
		}else {clear(x, y);
			return true;
		}
		
	}
	
	/*
	 * clear : 递归打开周围的空方块
	 * @param x,y 要打开的坐标
	 */
	
	public void clear(int x,int y) {
		if(x>=0&&x<he&&y>=0&&y<wi&&manage.haveNull(x, y)&&newCover[x][y]!=1) {
			setOpen(x, y);
			clear(x, y+1);
			clear(x+1, y);
			clear(x, y-1);
			clear(x-1, y);
		}
	}
	
	/*
	 * getCover : 获取遮盖层数组
	 * @return int[][] 遮盖层数组
	 */
	
	public int[][] getCover(){
		return newCover;
	}
	
	/*
	 * setFlag : 在指定坐标上插旗
	 * @param x,y 要插旗的坐标
	 * @return true为该局游戏已经胜利，false为还未胜利
	 */
	
	public boolean setFlag(int x,int y) {
		newCover[x][y]=2;
		return isSuc();
	}
	
	/*
	 * setOpen : 打开指定坐标的方块
	 * @param x,y 要打开的坐标
	 */
	
	public void setOpen(int x,int y) {
		newCover[x][y]=1;	
		Display.open(x,y,manage.getNum(x, y));
	}
	
	/*
	 * getNum : 获取给定坐标的遮盖状态
	 * @return 0为被遮盖状态，1为打开状态，2为插旗状态
	 */
	
	public int getNum(int x,int y) {
		return newCover[x][y];
	}
	
	/*
	 * isSuc : 检验该局是否胜利
	 * @return true为该局游戏已经胜利，false为还未胜利
	 */
	
	private boolean isSuc() {
		for(int i=0;i<wi;i++) 
			for(int j=0;j<he;j++) 
				if(manage.isMine(i, j))
					if(newCover[i][j]!=2)
						return false;
		return true;
	}
}
