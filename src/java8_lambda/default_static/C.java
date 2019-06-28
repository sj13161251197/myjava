package java8_lambda.default_static;

interface C{
	void printA();
	default void printA(int c) {
	}
	
}