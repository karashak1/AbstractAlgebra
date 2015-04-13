package util;

public interface SharedValues {
	
	static Object lock = new Object();

	static Character[] symbolValues = {0x2192,0x2283,0x2203,0x2200,0x2227,0x2228,0x00AC,0x02DC,0x0021,
		0x002A,0x002B,0x00B7,'0','1'};//symbols ->,c,E,A,^,upside down ^, -;,~,!,*,+,.,0,1
	
	static int[] symbolArity ={2,2,1,1,2,2,1,1,1,2,2,2,0,0};
	
	static Character[] variableValues ={'x','y','z','w'};
}
