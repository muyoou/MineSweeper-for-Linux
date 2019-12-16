package jblei;

/*
 * Display : 用于输出游戏画面
 * 
 */

public class Display {
	private MapManage mm;
	private Calculate ca;
	private int X=0,Y=0;
	
	public Display(MapManage mm,Calculate ca) {
		this.mm=mm;
		this.ca=ca;
	}
	
	/*
	 * printMap:输出完整的游戏界面
	 * @param wiSize 扫雷地图宽度
	 * 		  heSize 扫雷地图高度
	 */
	public static void printMap(int wiSize,int heSize) {
		System.out.print("\u001b[0m");
		printHead(wiSize);
		System.out.print("╔");
		for(int i=0;i<wiSize*2;i++) {
			System.out.print("═");
		}
		System.out.print("╗");
		System.out.print("\u001b[1000D");
		System.out.println();
		for(int i=0;i<wiSize;i++) {
			System.out.print("║");
			System.out.print("\u001b[47;1m");
			for(int j=0;j<heSize;j++) {
				System.out.print("▀");
				System.out.print(" ");
				
			}
			System.out.print("\u001b[0m");
			System.out.print("║");
			System.out.println();
			System.out.print("\u001b[1000D");
		}
		System.out.print("╚");
		for(int i=0;i<wiSize*2;i++) {
			System.out.print("═");
		}
		System.out.print("╝");
		printFoot(wiSize, heSize);
	}
	
	/*
	 * printHead : 输出扫雷头部提醒栏
	 * @param  wiSize 扫雷地图宽度
	 */
	
	private static void printHead(int wiSize) {
		for(int i=0;i<wiSize-3;i++) System.out.print(" ");
		System.out.println("♪(*^∇^)");
		System.out.print("\u001b[1000D");
	}
	
	/*
	 * printFoot : 输出扫雷尾部提醒栏
	 * @param  wiSize 扫雷地图宽度
	 * 		   heSize 扫雷地图高度
	 */
	
	private static void printFoot(int wiSize,int heSize) { 
		System.out.println();
		System.out.print("\u001b[1000D");
		System.out.println("难度："+wiSize+"*"+heSize);
		System.out.print("\u001b[1000D");
		System.out.println("说明：  方向键移动，回车键插旗，空格键开块");
		System.out.print("\u001b[1000D");
		
	}
	
	/*
	 * resetPos : 用于第一次输出后，设置X，Y初始坐标，并将光标移至左上角
	 * @param x 初始坐标水平位置
	 * 		  y 初始坐标垂直位置
	 */
	
	public void resetPos(int x,int y) {
		X=x;Y=y;
		ToZero();
		//ToPos(1, 1);
	}
	 
	/*
	 * ToZero : 用于第一次输出后，将光标移动到左上角
	 */
	
	private void ToZero() {
		System.out.print("\u001b[1000D");
		System.out.print("\u001b["+(3+mm.getHeight())+"A");
		System.out.print("\u001b[1C");
		
		System.out.print("\u001b[44;1m");
		System.out.print("▀ ");
		System.out.print("\u001b[0m");
		System.out.print("\u001b[2A");
		System.out.print("\u001b[3D");
		System.out.print("\u001b[s");
	}
	
	/*
	 * ToPos : 用于将光标用左上角移动到X，Y坐标处
	 */
	
	private void ToPos() {
		System.out.print("\u001b[1000D");
		System.out.print("\u001b["+(Y+2)+"B");
		System.out.print("\u001b["+(X*2+1)+"C");
	}
	
	/*
	 * ToPos : 用于将光标移动到给定的x，y坐标处
	 * @param x 给定要移动到的x坐标
	 * 		  y 给定要移动到的y坐标
	 */
	
	private static void ToPos(int x,int y) {
		System.out.print("\u001b[1000D");
		System.out.print("\u001b["+(y+2)+"B");
		System.out.print("\u001b["+(x*2+1)+"C");
	}
	
	/*
	 * back : 坐标回到左上角
	 */
	
	private static void back() {
		System.out.print("\u001b[u");
	}
	
	/*
	 * MoveR : 坐标向左或者右移动
	 * @param : r true为向右，false为向左
	 */
	
	public void MoveR(boolean r) {
		ToPos();
		ClearPos();
		if(r) {
			X++;
			PrintPos();
		}else {
			X--;
			System.out.print("\u001b[4D");
			PrintPos();
		}
		back();
	}
	
	/*
	 * MoveT : 坐标向上或者下移动
	 * @param : t true为向下，false为向上
	 */
	
	public void MoveT(boolean t) {
		ToPos();
		ClearPos();
		if(t) {
			Y++;
			System.out.print("\u001b[2D");
			System.out.print("\u001b[1B");
			PrintPos();
		}else {
			Y--;
			System.out.print("\u001b[2D");
			System.out.print("\u001b[1A");
			PrintPos();
		}
		back();
	}
	
	/*
	 * ClearPos : 清除坐标的选中状态
	 */
	
	private void ClearPos() {
		switch (ca.getNum(X, Y)) {
		case 2:
			System.out.print("\u001b[2C");
			break;
		case 1:
			System.out.print(mm.getNum(X, Y)+" ");
			break;

		default:
			System.out.print("\u001b[47;1m");
			System.out.print("▀ ");
			System.out.print("\u001b[0m");
		}
	}
	
	/*
	 * PrintPos : 将坐标设为选中状态
	 */
	
	private void PrintPos() {
		switch (ca.getNum(X, Y)) {
		case 2:
			System.out.print("\u001b[2C");
			break;
		case 1:
			System.out.print("\u001b[44;1m");
			System.out.print(mm.getNum(X, Y)+" ");
			System.out.print("\u001b[0m");
			break;
		default:
			System.out.print("\u001b[44;1m");
			System.out.print("▀ ");
			System.out.print("\u001b[0m");
		}
	}
	
	/*
	 * RefreshPos : 刷新坐标的选中状态
	 */
	
	public void RefreshPos() {
		ToPos();
		PrintPos();
		back();
	}
	
	/*
	 * open : 将一个坐标设为打开的状态
	 * @param x 要打开的坐标x
	 * 		  y 要打开的坐标y
	 * 		  num 打开后显示的数字
	 */
	
	public static void open(int x,int y,int num) {
		ToPos(x, y);
		System.out.print(num+" ");
		back();
		
	}
	
	/*
	 * AddFlag : 在现在的光标位置插旗
	 */
	
	public void AddFlag() {
		ToPos();
		System.out.print("\u001b[42m");
		System.out.print("▼ ");
		System.out.print("\u001b[0m");
		back();
	}
	
	/*
	 * Failed : 显示失败提示
	 */
	
	public void Failed() {
		System.out.print("\u001b[41m");
		System.out.print(" o(TヘTo) 炸了唉~ ");
		System.out.print("\u001b[0m");
	}
	
	/*
	 * Succeed : 显示成功提示
	 */
	
	public void Succeed() {
		System.out.print("\u001b[42m");
		System.out.print(" ヽ(✿ﾟ▽ﾟ)ノ     我好了！ ");
		System.out.print("\u001b[0m");
	}
	
	/*
	 * End : 将光标移动到最底部
	 */
	
	public void End() {
		back();
		System.out.print("\u001b["+(5+mm.getHeight())+"B");
	}
}
