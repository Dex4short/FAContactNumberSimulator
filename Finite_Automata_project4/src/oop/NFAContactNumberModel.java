package oop;



public abstract class NFAContactNumberModel {
	
	public NFAContactNumberModel() {
		// TODO Auto-generated constructor stub
	}
	public boolean isContactNumber(String input) {
		int state=0;
		
		for(char i: input.toCharArray()) {
			
			switch(state) {
			case 0:
				if(i=='+') state=1;
				else if(i=='0') state=2;
				else return false;
				break;
			case 1:
				if(i=='6') state=3;
				else return false;
				break;
			case 2:
				if(i=='9') state=10;
				else return false;
				break;
			case 3:
				if(i=='3') state=4;
				else return false;
				break;
			case 4:
				if(i==' ') state=5;
				else return false;
				break;
			case 5:
				if(i=='(') state=6;
				else if(i=='9') state=7;
				else return false;
				break;
			case 6:
				if(i=='9') state=17;
				else return false;
				break;
			case 7:
				if(isNumber(i)) state=8;
				else return false;
				break;
			case 8:
				if(isNumber(i)) state=9;
				else return false;
				break;
			case 9:
				if(i==' ') state=23;
				else return false;
				break;
			case 10:
				if(isNumber(i)) state=16;
				else return false;
				break;
			case 11:
				if(i==' ' || i=='-') state=24;
				else return false;
				break;
			case 12:
				if(isNumber(i)) state=13;
				else return false;
				break;
			case 13:
				if(isNumber(i))	state=14;
				else return false;
				break;
			case 14:
				if(isNumber(i)) state=15;
				else return false;
				break;
			case 15:
				return false;
			case 16:
				if(isNumber(i)) state=20;
				else return false;
				break;
			case 17:
				if(isNumber(i)) state=18;
				else return false;
				break;
			case 18:
				if(isNumber(i)) state=19;
				else return false;
				break;
			case 19:
				if(i==')') state=9;
				else return false;
				break;
			case 20:
				if(isNumber(i)) state=21;
				else if(i==' ') state=23;
				else return false;
				break;
			case 21:
				if(isNumber(i)) state=22;
				else return false;
				break;
			case 22:
				if(isNumber(i)) state=24;
				else return false;
				break;
			case 23:
				if(isNumber(i)) state=25;
				else return false;
				break;
			case 24:
				if(isNumber(i)) state=12;
				else return false;
				break;
			case 25:
				if(isNumber(i)) state=26;
				else return false;
				break;
			case 26:
				if(isNumber(i)) state=11;
				else return false;
				break;
			}
			onNextState(i, state);
		}
		return state==15;
	}
	public boolean isNumber(char i) {
		return i>='0' && i<='9';
	}
	public abstract void onNextState(char input,int state);
}

