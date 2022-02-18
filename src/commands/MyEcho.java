package commands;


public class MyEcho extends Thread {
	public MyEcho(String s) {
		f(s);
	}

	private void f(String x) {
		int count = 0;
		String output = "";
		char[] str = x.toCharArray();
		if(str[0] == '-' && str[1] == 'e') {
			for(int i = 3; i < str.length; i++) {
				if(str[i] == '\\') {
					switch(str[i + 1]) {
					case 'r': output = ""; i++;break;
					case 'b': // \b escape sequence does not work in eclipse console 
						output = output.substring(0, output.length() - 1);
						i++;
						break;
					case 'c': i = str.length - 1; i++;break;
					case 'n': output = output + "\n";i++;break;
					case 't': output = output + "\t";i++;break;
					case 'v':
						count++;
						output = output + "\n";
						for(int k = 0; k < count;k++) {output = output + "\t";}
						i++;break;
					}
				}
				else {
					output = output + str[i];
				}
			}
			System.out.println(output);
		}
		else {
			System.out.println(x);
		}	
	}
}
