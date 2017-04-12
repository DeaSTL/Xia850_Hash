import java.math.BigInteger;

public class Main {
	public static BigInteger getNumber(String input){
		String init = input;
		String prehash = "";
		BigInteger Bigprehash;
		for(int i = 0;i<init.length();i++){
			char tmp[] = init.toCharArray();
			prehash += tmp.hashCode();
		}
			
		Bigprehash = new BigInteger(prehash);
		return Bigprehash;	
	}
	public static BigInteger Compress(BigInteger input){
		String tmp = input.toString(2);
		int blockLength = 4096;
		int tmpDiff = 0;
		int tmpLength = tmp.length();
		
		BigInteger output = new BigInteger("1");
		if(tmp.length() < blockLength){
			tmpDiff = blockLength - tmp.length();
		}
		for(int i = 0;i<tmpDiff;i++){
			tmp += "0";
			 
		}
		String tmpLengthBin = new Integer(tmpLength).toString(2);
		tmp += tmpLengthBin;
		String tmpCollection = "";
		for(int i = 0; i < tmp.length();i++){
			tmpCollection += tmp.toCharArray()[i]; 
			if(i % 1024 == 0){
				output = output.xor(new BigInteger(tmpCollection));
				tmpCollection = "";
			}
		}
		return output;
		
	}
	public static String hashText(String input){
		String tmp = Compress(getNumber(input)).toString(16);
		return tmp;
	}
	
	public static void main(String[] args){
		String tmp = "password";
		for(int i = 0;i<10;i++){
			tmp = hashText(tmp);
		}
		System.out.println(tmp);
		System.out.println(tmp.length());
	}
	
}
