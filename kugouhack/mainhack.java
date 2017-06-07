package kugouhack;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class mainhack {
	//static byte[] keys={(byte) 172,(byte) 236,(byte) 223,(byte) 87};
	public static void main(String[] args) {
		//String fileName=args[0];
		//String output="new "+args[0];
		int[] keys={ 172, 236, 223, 87};
		String fileName="contrail.kgtemp";
		String output="new "+"test.mp3";
		File file = new File(fileName);
		InputStream in = null;
		int bj=0;
		int temp=0;
		int key=0;
		try {
			DataOutputStream out=new DataOutputStream(new FileOutputStream(output));
			in = new FileInputStream(file);
			in.skip(1024); 
			while ((temp = in.read()) != -1) {
				key=keys[bj];
				System.out.println(temp+' '+key);
				int keyhigh=(key >> 4);
				int keylow=(key & 0xF);
				int temphigh=(temp >> 4);
				int templow=(temp & 0xF);
				int anslow=(templow ^ keylow);
				int anshigh=(temphigh ^ keyhigh ^ anslow & 0xF);
				int ans=((anshigh << 4)+anslow);
				byte outbyte=0;
				if(ans<128){
					outbyte=(byte) ans;
				}else{
					outbyte=(byte) (ans-256);
				}
				out.writeByte(outbyte);
				bj++;
				bj =(bj % 4);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
