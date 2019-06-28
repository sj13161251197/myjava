package pattern23;
/**
 * 目的：继承好处是代码复用，但是也带来了坏处牵一发而动全身
 * 		可以通过类的组合代替继承而用的代码复用的实现   即策略模式的原则:如人可以思考和运动（每个人的思考方式和运动方式不一样，具体每个人是怎么思考的怎么运动的）
 * 	
 * 		实现列子：
 * @author Administrator
 *
 */
public class 策略模式 {//super  超类
	public static void main(String[] args) {
		Super s=new Sam(new SiKao1(),new Run2());
		s.run();
		s.sikao();
	}
}
class Super {//super  超类  角色
	SiKao sikao;
	Run run;
	void setDuo(SiKao don){
		this.sikao=don;
	}
	void setRun(Run run){
		this.run=run;
	}
	void sikao(){
		if(sikao!=null){
			sikao.sikao();
		}
	}
	void run(){
		if(run!=null){
			run.run();
		}
	}
}
class Sam extends Super{//角色对应的具体人
	Sam(SiKao sikao,Run run){
		this.sikao=sikao;
		this.run=run;
	}
}
interface SiKao{//人的思考
	void sikao();
}
interface Run{//人的动作
	void run();
}
class SiKao1 implements SiKao{//具体的思考

	@Override
	public void sikao() {
		// TODO Auto-generated method stub
		System.out.println("我最厉害");
	}
	
}
class SiKao2 implements SiKao{

	@Override
	public void sikao() {
		// TODO Auto-generated method stub
		System.out.println("我还可以");
	}
	
}
class Run1 implements Run{//具体的动作

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我跑的最快");
	}
	
}
class Run2 implements Run{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("我跑的飞起");
	}
	
}