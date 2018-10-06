package 泛型;

import java.util.ArrayList;
import java.util.List;

//�Զ��巺����
public class Order<T> {
	private String orderName;
	private int orderId;
	private T t;
	List<T> list = new ArrayList<>();
	
	public void add(){
		list.add(t);
	}
	public  T getT(){
		return t;
	}
	public void setT(T t){
		this.t = t;
	}
	//��������static������ʹ�÷��͵�����
//	public static void show(){
//		System.out.println(t);
//	}
	public void info(){
		//��������try-catch��ʹ����ķ��͵�����
//		try{
//			
//		}catch(T e){
//			
//		}
	}
	//�������ͷ���
	public static <E> E getE(E e){
		return e;
	}
	//ʵ�����鵽���ϵĸ���
	public <E> List<E> fromArrayToList(E[] e,List<E> list){
		for(E e1 : e){
			list.add(e1);
		}
		return list;
	}
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Order [orderName=" + orderName + ", orderId=" + orderId
				+ ", t=" + t + "]";
	}
}
//�̳з�������ͽӿ�ʱ������ָ�����͵�����
class SubOrder extends Order<Integer>{
	
}
