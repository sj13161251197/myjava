package nps;
/**
 * nps指令下发借口
 */
import java.util.ArrayList;
import java.util.List;

import net.TimeoutException;
import net.TimeoutThread;

public class MainCheck {
	
/**
* @param args
*/
	public static void main(String[] args) {
	
		//初始化超时类
		System.out.println("main");
		TimeoutThread t = new TimeoutThread(1000,new TimeoutException("超时"));
		System.out.println("t");
		try{
			System.out.println("t.start()-begin");
			t.start();
			System.out.println("t.start()-end"); 
			//.....要检测超时的程序段....
			getMothod();
			t.cancel();
			//System.out.println("t.fail="+t.isfail);
			System.out.println("t.cancel()-end");
		}catch (Exception e){
			//按道理讲应该这里可以捕获到这个异常，可是怎么都进不来
			System.out.println("eeeeeeeeeeeee");
			//...对超时的处理...
	
		}
		System.out.println("main==end");
	}

	public static void getMothod() {
		while (true) {

		}
	}
	byte[] initByte() {
		byte[] byteArr = new byte[121];
		//设置包头
		byteArr[0]=-52;
		byteArr[1]=-1;
		
		//设备包长
		byte[] dateLen = getDateLen(16);
		byteArr[2]=dateLen[0];//包长的计算方法
		byteArr[3]=dateLen[1];
		//456位为校验和相关在最后处理
		//设备类型
		byteArr[7]=1;
		
		//操作命令
		byteArr[8]=5;
		//操作类型
		byteArr[9]=1;//下发卡
		//操作通道
		byteArr[10]=1;//那个箱子
		//卡类型
		byteArr[11]=2;//普通卡
		//默认值保留
		byteArr[12]=0;
		//卡号
		String cardId="12345678";//16进制8位的数据
		byte[] cardIdArr = getCardId(cardId);
		byteArr[13]=cardIdArr[0];
		byteArr[14]=cardIdArr[1];
		byteArr[15]=cardIdArr[2];
		byteArr[16]=cardIdArr[3];
		//箱门
		String boxStr="1_2_3_4_5";//16进制8位的数据
		List<Byte> getBoxSum = getBoxSum(boxStr);
		for(int i=0;i<getBoxSum.size();i++) {
			byteArr[24-i]=getBoxSum.get(i);
		}
		//时段
		byteArr[25]=getTimeSum("1_2_3_4_5");
		
		//卡密码
		String cardIdPass="12345678";//16进制8位的数据
		byte[] cardIdPassArr = getCardPassId(cardId);
		byteArr[26]=cardIdPassArr[0];
		byteArr[27]=cardIdPassArr[1];
		byteArr[28]=cardIdPassArr[2];
		
		
		//先设置数据最后计算校验和
		byte sum = getSum(byteArr);
		byteArr[4]=sum;
		
		//校验和处理得到密码
		byte[] passArr = getPass(sum);
		byteArr[5]=passArr[0];//包长的计算方法
		byteArr[6]=passArr[1];
		//尾部校验和
		byteArr[29]=getAllSum(sum);
		return byteArr;
		
	}
	private List<Byte> getBoxSum(String boxStr) {
		// TODO Auto-generated method stub
		String[] split = boxStr.split("_");
		List<Byte> list = new ArrayList<Byte>();
		int sum=0;
		for(int i=0;i<split.length;i++) {
			int n=2<<i;
			sum+=n;
			if(n>=64) {
				list.add((byte)n);
			}
			list.add((byte)sum);
		}
		return list;
	}
	private byte getTimeSum(String boxStr) {
		// TODO Auto-generated method stub
		String[] split = boxStr.split("_");
		
		int sum=0;
		for(int i=0;i<split.length;i++) {
			sum+=2<<i;
		}
		return (byte)sum;
	}
	byte[] getDateLen(int len) {
		byte[] byteArr = new byte[2];
		//byteArr[0]=(byte)(11+dataArr.length);
		byteArr[0]=(byte)(27);
		byteArr[1]=(byte)0;
		return byteArr;
	}
	byte[] getPass(byte sum) {
		byte[] byteArr = new byte[2];
		//byteArr[0]=(byte)(11+dataArr.length);
		byteArr[0]=(byte)(27);
		byteArr[1]=(byte)0;
		return byteArr;
	}
	byte getSum(byte[] arr) {//得到不包含包头的数据校验和
		int sum=0;
		for(int i=7;i<=28;i++) {
			if(arr[i]<0) {
				sum+=(arr[i]+256);
			}else {
				sum+=arr[i];
			}
		}
		return (byte)sum;
	}
	byte getAllSum(byte sum) {//得到全部数据的校验和
		int tou=459;//包头和是固定不变的
		if(sum<0) {
			return (byte)(sum+tou+256);
		}
		return (byte)(sum+459);
	}
	byte[] getCardId(String cardid) {
		byte[] byteArr = new byte[4];
		long parseLong = Long.parseLong(cardid,16);
		byteArr[0]=(byte)(parseLong&0xff);
		byteArr[1]=(byte)((parseLong>>8)&0xff);
		byteArr[2]=(byte)((parseLong>>16)&0xff);
		byteArr[3]=(byte)((parseLong>>24)&0xff);
		return byteArr;
	}
	byte[] getCardPassId(String password) {
		byte[] passwordArray = new byte[3]; // 密码，只支持数字
		String password1 = password.substring(0, 2);
		String password2 = password.substring(2, 4);
		String password3 = password.substring(4, 6);
		passwordArray[0] = (byte) Long.parseLong(password1, 16); // 65
		passwordArray[1] = (byte) Long.parseLong(password2, 16);
		passwordArray[2] = (byte) Long.parseLong(password3, 16);

		return passwordArray;
		
	}

}