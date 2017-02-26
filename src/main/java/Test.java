
public class Test {

	static String profilepath1 = "";  //"./resources/message.properties";
	
	public  void file(String file){
		Test.profilepath1 = file;
		System.out.println(profilepath1);
	}

	 public static void main(String[] args) {
		Test t = new Test();
		t.file("./resources/message.properties");
	}
}
