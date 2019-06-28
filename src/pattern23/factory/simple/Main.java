package pattern23.factory.simple;

public class Main {
	public static void main(String[] args) {
		INoodles noodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_GK);
		 noodles.desc();
	}
}
