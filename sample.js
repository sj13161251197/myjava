var BigDecimal = Java.type('java.math.BigDecimal'); 

function calculate(amount, percentage) { 
   var result = new BigDecimal(amount).multiply( 
      new BigDecimal(percentage)).divide( 
         new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN); 
   return result.toPlainString(); 
}
var result = calculate(568000000000000000023,13.9); 
print(result);
var fun1 = function(name) {
    print('Hello ' + name);
    return "Hi!";
}

var fun2 = function (object) {
    print(Object.prototype.toString.call(object));
}; 
var MyJavaClass = Java.type(`java8_lambda.Nashorn_jjs`);

var result = MyJavaClass.sayHello('Nashorn');
print(result);