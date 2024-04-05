package com.kunning.javase.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 此类用来讲解对象流的使用
public class ObjectIOStream {

	// 将 Person 对象序列化到本地文件"ObjectIOStream.txt"中
	@Test
	public void objectOutputStream() throws IOException {

		Person preson = new Person("花花", 4);

		FileOutputStream fileOutStream = new FileOutputStream(new File("ObjectIOStream.txt"));
		ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
		objOutStream.writeObject(preson);

		objOutStream.close();
	}

	// 从本地的序列化文件"ObjectIOStream.txt"中反序列化出Person对象
	@Test
	public void objectInputStream() throws IOException, ClassNotFoundException {

		FileInputStream fileInStream = new FileInputStream(new File("ObjectIOStream.txt"));
		ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
		Person person = (Person) objInStream.readObject();
		System.out.println(person);

		objInStream.close();
	}

}
