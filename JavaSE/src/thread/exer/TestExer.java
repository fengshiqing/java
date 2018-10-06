package thread.exer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TestExer {
	//�ַ������� test.txt Ϊ test1.txt
	@Test
	public void test4(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			br = new BufferedReader(new FileReader(new File("test.txt")));
			bw = new BufferedWriter(new FileWriter(new File("test2.txt")));
			
			char[] c = new char[20];
			int len;
			while((len = br.read(c)) != -1){
				bw.write(c, 0, len);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//ʹ���ַ���ʵ�����ݵĶ���
	@Test
	public void test3(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("test.txt"));
			String str;
			while((str = br.readLine()) != null){
				System.out.println(str);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	//ʹ���ַ���ʵ�����ݵ����
	@Test
	public void test2(){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("test1.txt"));
			String str = "Java��һ�ֿ���׫д��ƽ̨Ӧ��������������ĳ���������ԣ�\n" +
					"����Sun Microsystems��˾��1995��5���Ƴ���Java����������Ժ�\n" +
					"Javaƽ̨����JavaSE, JavaEE, JavaME�����ܳơ�Java ��������׿" +
					"Խ��ͨ���ԡ���Ч�ԡ�ƽ̨��ֲ�ԺͰ�ȫ�ԣ��㷺Ӧ���ڸ���PC���������ġ���Ϸ����̨��" +
					"��ѧ������������ƶ��绰�ͻ�������ͬʱӵ��ȫ�����Ŀ�����רҵ��Ⱥ����ȫ���Ƽ���" +
					"���ƶ��������Ĳ�ҵ�����£�Java���߱����������ƺ͹���ǰ����";
			bw.write(str);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	// ʹ���ֽ���ʵ�����ݵ����
	@Test
	public void test1() {
		// FileOutputStream fos = new FileOutputStream(new File("test.txt"));
		// BufferedOutputStream bos = new BufferedOutputStream(fos);
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(
					new FileOutputStream(new File("test.txt")));
			String str = "Java��һ�ֿ���׫д��ƽ̨Ӧ��������������ĳ���������ԣ�\n" +
					"����Sun Microsystems��˾��1995��5���Ƴ���Java����������Ժ�\n" +
					"Javaƽ̨����JavaSE, JavaEE, JavaME�����ܳơ�Java ��������׿" +
					"Խ��ͨ���ԡ���Ч�ԡ�ƽ̨��ֲ�ԺͰ�ȫ�ԣ��㷺Ӧ���ڸ���PC���������ġ���Ϸ����̨��" +
					"��ѧ������������ƶ��绰�ͻ�������ͬʱӵ��ȫ�����Ŀ�����רҵ��Ⱥ����ȫ���Ƽ���" +
					"���ƶ��������Ĳ�ҵ�����£�Java���߱����������ƺ͹���ǰ����";
			
			bos.write(str.getBytes());
			bos.flush();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
