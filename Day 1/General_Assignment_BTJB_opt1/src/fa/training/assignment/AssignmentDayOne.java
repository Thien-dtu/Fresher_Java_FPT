package fa.training.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//		sc.close();
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
		System.out.print("S=1+1/2+1/3+....+1/n la : " + s + "\n");
//		sc.close();
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
//		sc.close();
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
//		sc.close();
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
//		sc.close();
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
		System.out.println();
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
//		sc.close();
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

	private static void occurrenceFrequencyStringFunc(String s) {
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
//		sc.close();

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
		occurrenceFrequencyStringFunc(inputString);
		System.out.println();
		subStringFunc(inputString);
		System.out.println();
//		sc.close();
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
				sortASC(arr);
				show(arr);
				break;
			case 4:
				insertK(arr);
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
//		scan.close();
//		sc.close();
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
	
	private static void sortASC(int[] arr) {
		int temp = arr[0];
        for (int i = 0 ; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
	}
	
	public static void insertK(int [] arr) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap vao phan tu K can chen: ");
		int k = sc.nextInt();
        int arrIndex = arr.length - 1;
        int tempIndex = arr.length;
        int [] tempArr = new int [tempIndex + 1];
        boolean inserted = false;
        
        System.out.println("Mang sau khi sap xep tang dan la: ");
        sortASC(arr);
        show(arr);
         
        for (int i = tempIndex; i >= 0; i--) {
            if (arrIndex > -1 && arr[arrIndex] > k) {
                tempArr[i] = arr[arrIndex--];
            } else {
                if (!inserted) {
                    tempArr[i] = k;
                    inserted = true;
                } else {
                    tempArr[i] = arr[arrIndex--];
                }
            }
        }
        System.out.println("Mang sau khi sap xep tang dan va chen phan tu k = " + k + " ( van dam bao mang tang dan ) la: ");
        show(tempArr);
    }
	
	public static void show(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

	private static void programFuncExerciseEigh() {
		inputAray();
	}
	
	private static void inputMatrix() {
		Scanner sc = new Scanner(System.in);
        System.out.print("Moi ban nhap vao so dong: ");
        int m = sc.nextInt();
        System.out.print("Moi ban nhap vao so cot: ");
        int n = sc.nextInt();
        int[][] A = new int[m][n];
        System.out.println("Moi ban nhap cac phan tu cua ma tran A: ");
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            A[i][j] = sc.nextInt();
          }
        }
        
        Scanner scan = new Scanner(System.in);
		boolean cont = true;
		do {
			System.out.println("Chọn chức năng : [1 - 2]");
			System.out.println("1. Tính tích các số là bội số của 3 nằm trên dòng đầu tiên của ma trận ");
			System.out.println("2. Tạo ra mảng một chiều X với X[i] là các giá trị lớn nhất trên từng dòng i của ma trận A.");
			System.out.println("Nhap bat ky phim nao ngoai tru phim 1, 2 de thoat");

			int chon = scan.nextInt();
			switch (chon) {
			case 1:
				multi3FirstLineOfMatrix(A, n);
				break;
			case 2:
				maxValueOfOneDimensionalMatrix(A, n, m);
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
//		scan.close();
        
	}
	
	private static void multi3FirstLineOfMatrix(int [] [] A, int n) {
		int product = 1;
		for (int j = 0; j < n; j++) {
	          if (A[0][j] % 3 == 0) {
	            product *= A[0][j];
	          }
	        }
		System.out.println("Tích các số là bội số của 3 nằm trên dòng đầu tiên của ma trận : " + product);
	}
	
	private static void maxValueOfOneDimensionalMatrix(int [] [] A, int n, int m) {
		int[] X = new int[m];
        for (int i = 0; i < m; i++) {
          int max = A[i][0];
          for (int j = 1; j < n; j++) {
            if (A[i][j] > max) {
              max = A[i][j];
            }
          }
          X[i] = max;
        }
        
        System.out.print("Array X: [ ");
        for (int i = 0; i < m; i++) {
          System.out.print(X[i] + " ");
        }
        System.out.println("]");
	}
	
	private static void matrix() {
		Scanner scan = new Scanner(System.in);
		boolean cont = true;
		do {
			System.out.println("Chọn chức năng : [1 - 3]");
			System.out.println("1. Nhập ma trận A (m dòng, n cột) gồm các phần tử kiểu int ");
			System.out.println("2. Tính tích các số là bội số của 3 nằm trên dòng đầu tiên của ma trận ");
			System.out.println("3. Tạo ra mảng một chiều X với X[i] là các giá trị lớn nhất trên từng dòng i của ma trận A.");
			System.out.println("Nhap bat ky phim nao ngoai tru phim 1, 2 de thoat");

			int chon = scan.nextInt();
			switch (chon) {
			case 1:
				inputMatrix();
				break;
			case 2:
				System.out.println("	Moi ban thuc hien chuc nang 1 truoc!");
				System.out.println();
				break;
			case 3:
				System.out.println("	Moi ban thuc hien chuc nang 1 truoc!");
				System.out.println();
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
//		scan.close();
	}
	
	private static void readCSV() {
		String fileName = "D:\\fpt\\java_2023\\Day_Study\\JAVA\\Day 1\\General_Assignment_BTJB_opt1\\src\\fa\\training\\assignment\\STD.csv";
        String line = "";
        String csvSplitBy = ",";
        
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Pattern phonePattern = Pattern.compile("^[0-9]{10}$");
        int lineNumber = 0;
        String errorFile = "D:\\fpt\\java_2023\\Day_Study\\JAVA\\Day 1\\General_Assignment_BTJB_opt1\\src\\fa\\training\\assignment\\error.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
            	lineNumber++;
            	
            	String[] student = line.split(csvSplitBy);
            	// Check if the phone number is valid
                Matcher phoneMatcher = phonePattern.matcher(student[2]);
                if (!phoneMatcher.matches()) {
                    writeError(errorFile, "invalid phone number", lineNumber);
                }

                // Check if the email is valid
                Matcher emailMatcher = emailPattern.matcher(student[3]);
                if (!emailMatcher.matches()) {
                    writeError(errorFile, "invalid email", lineNumber);
                }
                System.out.println("StdNo: " + student[0] + ", StdName: " + student[1] + ", StdPhone: " + student[2] + ", StdEmail: " + student[3] + ", GradePoint: " + student[4] + "." );
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("The CSV file was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
        }
	}
	
	private static void writeError(String fileName, String error, int lineNumber) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write("Line" + lineNumber + ": " + error + " for student");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the error file.");
        }
    }

	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
			boolean cont = true;
			do {
				System.out.println("Chọn chức năng : [1 - 11]");
				System.out.println("1. Bài 1 ");
				System.out.println("2. Bài 2 ");
				System.out.println("3. Bài 3 ");
				System.out.println("4. Bài 4 ");
				System.out.println("5. Bài 5 ");
				System.out.println("6. Bài 6 ");
				System.out.println("7. Bài 7 ");
				System.out.println("8. Bài 8 ");
				System.out.println("9. Bài 9 ");
				System.out.println("10. Bài 10 ");
				System.out.println("11. Bài 11 ");
				System.out.println("Nhap bat ky phim nao ngoai tru phim 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 de thoat");
				int chon = scan.nextInt();
				switch (chon) {
				case 1:
					townStart();
					break;
				case 2:
					sumOnePlusOneN();
					break;
				case 3:
					sumOnePlusOneTwoNSubOneFactorial();
					break;
				case 4:
					runSumAndMultInLine();
					break;
				case 5:
					printUCLNAndBCNN();
					break;
				case 6:
					useChangeIntToBinar();
					break;
				case 7:
					stringFunc();
					break;
				case 8:
					programFuncExerciseEigh();
					break;
				case 9:
					matrix();
					break;
				case 10:
					readCSV();
					break;
				case 11:
					System.out.println("Sắp làm rồi");
					break;
				default:
					System.out.println("Tạm biệt");
					cont = false;
					break;
				}
			} while (cont);
			scan.close();
	}
}
