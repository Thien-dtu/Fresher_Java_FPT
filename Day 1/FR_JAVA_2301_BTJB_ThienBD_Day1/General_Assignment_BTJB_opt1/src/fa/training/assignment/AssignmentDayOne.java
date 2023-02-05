package fa.training.assignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AssignmentDayOne {

	public static void townStart() {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		do {
			System.out.println("Moi nhap vao so n: ");
			n = sc.nextInt();
		} while (n < 0);
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		sc.close();
	}

	public static void sumOnePlusOneN() {
		int n;
		float s = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Nhap vao so n: ");
			n = sc.nextInt();
		} while (n < 0);

		for (float i = 1; i <= n; i++) {
			s += 1 / i;
		}
		System.out.print("S=1+1/2+1/3+....+1/n la : " + s);
		sc.close();
	}

	public static void sumOnePlusOneTwoNSubOneFactorial() {
		float s = 0;
		int n;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Nhap vao so n: ");
			n = sc.nextInt();
		} while (n < 0);

		for (int i = 0; i <= n; i++) {
			s += 1.0 / (2 * i + 1);
		}

		System.out.println("S = 1+1/3!+1/5!+…..+1/(2n-1)! = " + s);
		sc.close();
	}

	public static void sumAllNumInLine(int n) {
		int temp = 0, s = 0;
		while (n > 0) {
			temp = n % 10;
			s += temp;
			n /= 10;
		}
		System.out.println("\n S = " + s);
	}

	public static void multiplyNumInLine(int n) {
		int s = 1;
		while (n != 0) {
			s *= n % 10;
			n /= 10;
		}
		System.out.println("\n P = " + s);
	}

	public static void runSumAndMultInLine() {
		int n;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Moi ban nhap vao so m: ");
			n = sc.nextInt();
		} while (n < 0);
		System.out.println("m = " + n + " => ");
		sumAllNumInLine(n);
		multiplyNumInLine(n);
		sc.close();
	}

	public static int UCLN(int a, int b) {
		while (a != b) {
			if (a > b) {
				a -= b;
			} else {
				b -= a;
			}
		}
		return a;
	}

	public static void printUCLNAndBCNN() {
		int a, b;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Nhap vao so a: ");
			a = sc.nextInt();
		} while (a < 0);
		do {
			System.out.print("Nhap vao so b: ");
			b = sc.nextInt();
		} while (b < 0);

		System.out.println("Uoc chung lon nhat cua " + a + " va " + b + " la: " + UCLN(a, b));

		System.out.println("Boi chung nho nhat cua " + a + " va " + b + " la: " + ((a * b) / UCLN(a, b)));
		sc.close();
	}

	public static void changeIntToBinar(int num) {
		int binary[] = new int[40];
		int index = 0;
		while (num > 0) {
			binary[index++] = num % 2;
			num = num / 2;
		}
		for (int i = index - 1; i >= 0; i--) {
			System.out.print(binary[i]);
		}
	}

	public static void useChangeIntToBinar() {
		int n;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Moi ban nhap vao so nguyen duong n: ");
			n = sc.nextInt();
		} while (n < 0);
		System.out.print("So nhi phan cua " + n + " la: ");
		changeIntToBinar(n);
		sc.close();
	}

	public static void stringReverseFunc(String s) {
		StringBuilder input1 = new StringBuilder();
		input1.append(s);
		input1 = input1.reverse();
		for (int i = 0; i < input1.length(); i++)
			System.out.print(input1.charAt(i));
		System.out.println();
	}

	public static void stringUpperCaseFunc(String s) {
		System.out.println(s.toUpperCase());
	}

	public static void stringLowerCaseFunc(String s) {
		System.out.println(s.toLowerCase());
	}

	private static void occurrenceFrequencytringFunc(String s) {
		HashMap<Character, Integer> eachCharCountMap = new HashMap<Character, Integer>();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (eachCharCountMap.containsKey(c)) {
				eachCharCountMap.put(c, eachCharCountMap.get(c) + 1);
			} else {
				eachCharCountMap.put(c, 1);
			}
		}
		System.out.println(eachCharCountMap);
	}

	private static void subStringFunc(String s) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Moi ban nhap vi tri thu n: ");
		int n = sc.nextInt();
		System.out.print("Moi ban nhap vi tri thu m: ");
		int m = sc.nextInt();
		System.out
				.println("Chuoi sau khi cat tu vi tri thu " + n + " va thu " + m + " la: " + s.substring(n - 1, m - 1));
		sc.close();

	}

	private static void stringFunc() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Moi ban nhap vao chuoi: ");
		String inputString = sc.nextLine();
		System.out.println("Chuoi sau khi dao nguoc la: ");
		stringReverseFunc(inputString);
		System.out.println("Chuoi sau khi viet hoa la: ");
		stringUpperCaseFunc(inputString);
		System.out.println("Chuoi sau khi viet thuong la: ");
		stringLowerCaseFunc(inputString);
		System.out.println("Tan suat xuat hien cua cac tu trong chuoi la: ");
		occurrenceFrequencytringFunc(inputString);
		System.out.println();
		subStringFunc(inputString);
		System.out.println();
		sc.close();
	}

	private static void inputAray() {
		Scanner sc = new Scanner(System.in);
		int n;
		do {
			System.out.println("Moi nhap so phan tu cua mang (>=0): ");
			while (!sc.hasNextInt()) {
				System.out.println("Nhap so vao di, pls ?");
				sc.next();
			}
			n = sc.nextInt();
		} while (n <= 0);
		int[] arr = new int[n];
		System.out.print("Nhập các phần tử của mảng: \n");
		for (int i = 0; i < n; i++) {
			System.out.printf("a[" + (i+1) + "] = ");
			arr[i] = sc.nextInt();
		}
		
		Scanner scan = new Scanner(System.in);
		boolean cont = true;
		do {
			System.out.println("Chọn chức năng : [1- 5]");
			System.out.println("1. Tính tổng số dương lẻ của mảng a");
			System.out.println("2. Nhập vào phần tử k, tìm xem k có xuất hiện trong mảng a không. Nếu có chỉ ra các vị trí của k trong mảng.");
			System.out.println("3. Sắp sếp mảng a theo thứ tự tăng dần.");
			System.out.println("4. Chèn một số p vào mảng a sao cho mảng a vẫn đảm bảo tăng dần.");
			System.out.println("Nhap bat ky phim nao ngoai tru phim 1, 2 ,3 ,4 de thoat");

			int chon = scan.nextInt();
			switch (chon) {
			case 1:
				sumNumPosOdd(arr, n);
				break;
			case 2:
				findK(arr, n);
				break;
			case 3:
				System.out.println("Thực hiện chức năng 3");
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
		scan.close();
		sc.close();
	}

	private static void sumNumPosOdd(int[] arr, int n) {
		int s = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] % 2 != 0) {
				s += arr[i];
			}
		}
		System.out.println("Tong cac so duong le la: " +s);
	}
	
	private static void findK(int[] arr, int n) {
		Scanner sc = new Scanner(System.in);
		int s = 0;
		int k;
		System.out.print("Moi nhap vao phan tu k can tim: ");
		System.out.println();
		System.out.println();
		k = sc.nextInt();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			if (arr[i] == k) {
				if (s == 0) {
					sb.append("vi tri xuat hien cua phan tu: " + k + " la: ");
				}
				sb.append(i+1).append(",");
				s++;
			}
		}
		sb.replace(sb.length()-1, sb.length(), "");
		
		System.out.println(sb.toString());
		System.out.println();
//		sc.close();
	}
	
	private static void sortASC() {
		
	}

	private static void programFuncExerciseEigh() {
		inputAray();
	}

	public static void main(String[] args) {
//		townStart();
//		sumOnePlusOneN();
//		sumOnePlusOneTwoNSubOneFactorial();
//		runSumAndMultInLine();
//		printUCLNAndBCNN();
//		useChangeIntToBinar();
//		stringFunc();
		programFuncExerciseEigh();

	}

}
