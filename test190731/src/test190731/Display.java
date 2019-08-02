package test190731;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
public class Display extends JFrame implements ActionListener {
	Vector<Person> total = new Vector<>();
	Person person = new Person();
	Panel p1, p2, btnPanel;
	List list;
	TextField tf;
	JButton button[] = new JButton[6];

	Display() {
		setSize(300,200);
		super.setResizable(true);
		setLayout(new BorderLayout());
		setTitle("주소 프로그램");
		p1 = new Panel();
		p1.setLayout(new BorderLayout());
		btnPanel = new Panel();
		btnPanel.setLayout(new GridLayout(6, 1));
		p1.add(list = new List(), "Center");
		String menuStr[] = {"추가", "수정", "삭제", "보기", "검색", "입력"};

		for(int i=0; i<button.length; i++) {
			button[i] = new JButton(menuStr[i]);
			btnPanel.add(button[i]);
			button[i].addActionListener(this);
		}
		p1.add(btnPanel, "East");
		add(p1, "Center");
		p2 = new Panel();
		p2.setLayout(new GridLayout(1, 1));
		p2.add(tf = new TextField());
		add(p2, "South");
		setVisible(true);
		tf.requestFocus();
	}
	String countArr[] = {"이름 = ", "주소 = ", "연락처 = ", "메일 = ", "학과 = ", "학번 = "};

	int count = 0;
	String temp = "";
	int checkNum = -1;
	boolean checkStatus = false;
	boolean checkContinue = false;
	int deviseNum = 0;
	public void startAction() {
		for(int i=0; i<button.length-1; i++) {
			button[i].setEnabled(true);
		}
		list.removeAll();
		checkNum = -1;
		temp = "";
		count = 0;
		person = new Person();
		deviseNum = 0;
	}
	void btnEnable(boolean check) {
		for(int i=0; i<button.length-1; i++) 
			button[i].setEnabled(check);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[0]) {
			checkNum = 0;
			btnEnable(false);
			temp = countArr[count];
			list.add(temp);
			/*button[5].addActionListener(new ActionListener() {					
		            public void actionPerformed(ActionEvent e) {
		            }
		        });*/
		} else if(e.getSource() == button[1]) {
			btnEnable(false);
			button[1].setEnabled(true);
			if(count!=0) {
				for(int i=0; i<person.str.length; i++) {
					person.getPerson(i, list.getItem(i).split("=")[1].trim());
				}
				total.remove(deviseNum);
				total.add(deviseNum, person);
				startAction();
				tf.setText("");
			} else {

				for(int i=0; i<total.size(); i++) {
					list.add(total.get(i).str[0]);	
				}
				tf.setText("수정할 이름을 선택하고 입력을 눌러주세요.");
				checkNum = 1;
				count++;
			}
		} else if(e.getSource() == button[2]) {
			btnEnable(false);
			button[2].setEnabled(true);
			if(count!=0) {
				startAction();
				tf.setText("");
			} else {
				for(int i=0; i<total.size(); i++) {
					list.add(total.get(i).str[0]);	
				}
				tf.setText("삭제할 이름을 선택하고 입력을 눌러주세요.");
				checkNum = 2;
				count++;
			}
		} else if(e.getSource() == button[3]) {
			btnEnable(false);
			button[3].setEnabled(true);
			if(count!=0) {
				startAction();
				tf.setText("");
			} else {
				for(int i=0; i<total.size(); i++) {
					list.add(total.get(i).str[0]);	
				}
				tf.setText("자세히 볼 이름을 선택하고 입력을 눌러주세요.");
				checkNum = 3;
				count++;
			}

		} else if(e.getSource() == button[4]) {
			btnEnable(false);
			button[4].setEnabled(true);
			if(count!=0) {
				startAction();
				tf.setText("");
			} else {

				tf.setText("검색할 이름을 입력하고 입력을 눌러주세요.");
				checkNum = 4;
				count++;
			}
		} else if(e.getSource() == button[5]) {
			switch(checkNum) {
			case 0 : {				

				list.remove(count);
				String strTemp = tf.getText();
				strTemp.trim();
				if(strTemp.equals("")) {
					strTemp = "-";
				}
				person.getPerson(count, strTemp);

				list.add(temp + strTemp);

				tf.setText("");
				
					
				if(count == countArr.length-1) {					
					total.add(person);
					startAction();
					tf.setText("입력 완료");

				} else  {
					count++;
					temp = countArr[count];
					list.add(temp);
				}
				break;
			}
			case 1 : {
				person = new Person();

				if(count==1) {
					
					for(int i=0; i<total.size(); i++) 
						if(list.getSelectedItem().equals(total.get(i).str[0])) {
							person = total.get(i);
							deviseNum = i;
							break;
						}
					list.removeAll();
					for(int i=0; i<person.str.length; i++) {
						list.add(countArr[i] + person.str[i]);
					}

					count++;
				} else if(count ==2) {
					
					String temp = list.getSelectedItem();




					tf.setText(list.getSelectedItem().split(" = ")[1]);
					count++;


				} else if(count==3) {
					
					String temp = list.getSelectedItem();


					list.add(countArr[list.getSelectedIndex()]+tf.getText(),list.getSelectedIndex()+1);
					list.remove(list.getSelectedIndex());


					count--;
				}
				break;
			}
			case 2 : {
				if(count==1) {					
					int temp = list.getSelectedIndex();
					list.remove(temp);
					total.remove(temp);
					tf.setText("삭제 완료");

				}
				break;
			}
			case 3 : {
				person = new Person();
				int tempNum = 0;
				if(count==1) { // 낱개가 나열된 상태
					for(int i=0; i<total.size(); i++) 
						if(list.getSelectedItem().equals(total.get(i).str[0])) {
							person = total.get(i);
							tempNum = i;
							break;
						}
					list.removeAll();
					for(int i=0; i<person.str.length; i++) {
						list.add(countArr[i] + person.str[i]);
					}
					tf.setText("처음화면으로 가기위해 보기를 눌러주세요");
				}
				break;
			}
			case 4 : {
				person = new Person();
				int tempNum = 0;
				String str = tf.getText();
				if(count==1) { // 낱개가 나열된 상태
					for(int i=0; i<total.size(); i++) 
						if(str.equals(total.get(i).str[0])) {
							person = total.get(i);
							tempNum = i;
							break;
						}
					for(int i=0; i<person.str.length; i++) {
						list.add(countArr[i] + person.str[i]);
					}
				}
				break;
			}
			}
		}
	}
	public static void main(String[] args) {
		new Display();
	}	
}
class Person {
	String str[] = new String[6];
	
	Person() {
		
	}
	void getPerson(int num, String str) {
		this.str[num] = str;
	}
}