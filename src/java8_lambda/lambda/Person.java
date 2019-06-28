package java8_lambda.lambda;

public class Person extends Human{
	String food;
	void eat(){
		System.out.println("我要吃好吃的");
	}
	public String getFood() {
		System.out.println("我有好吃的");
		return "水果";
	}

	public void setFood(String food) {
		this.food = food;
	}
	
}
