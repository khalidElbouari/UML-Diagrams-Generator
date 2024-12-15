package org.mql.scanner;

public class Exemples {

public Exemples() {
	exp01();
}

	 void exp01() {
		 Scanner scanner = new Scanner();
	        scanner.scan("org.mql.school"); 
	        scanner.displayPackageDiagram();	
}

	public static void main(String[] args) {
		new Exemples();
	}
}
