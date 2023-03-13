import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main { // 2번 문제
	
	static List<Post> posts = new ArrayList<>();
	static int lastPostId = 3;
	
	public static void main(String[] args) {
		
		makeTestData();
	    Scanner sc = new Scanner(System.in);
	    
	    System.out.println("==프로그램 시작==");
	    
		while (true) {
			
			
			System.out.print("명령어 ) ");
			String command = sc.nextLine();
			
			
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			}
			
			if (command.equals("exit")) {
				System.out.println("==프로그램을 종료==");
			}
			
			if (command.equals("post write")) {
				System.out.print("제목: ");
				String title = sc.nextLine();
				System.out.print("내용: ");
				String body = sc.nextLine();
				
				String regDate = GetTimeUtil.getNow();
				int id = ++lastPostId;
				posts.add(new Post(id, regDate, title, body));
				
			} else if (command.equals("post list")) {
				System.out.println("번호 |   제목");
				for (int i = posts.size() - 1; i >= 0; i--) {
					Post post = posts.get(i);
					System.out.printf("  %d |   %s\n", post.id, post.title);
				}
				
			} else if (command.startsWith("post detail")) {
				String[] cmdDiv = command.split(" ");
				int id = Integer.parseInt(cmdDiv[2]);
				Post post = getPostById(id);
				if (post == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				System.out.printf("번호 : %d\n", post.id);
				System.out.printf("날짜 : %s\n", post.regDate);
				System.out.printf("제목 : %s\n", post.title);
				System.out.printf("내용 : %s\n", post.body);
				continue;
				
			} else if (command.startsWith("post modify")) {
				String[] cmdDiv = command.split(" ");
				int id = Integer.parseInt(cmdDiv[2]);
				Post post = getPostById(id);
				if (post == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				
			} else if (command.startsWith("post delete")) {
				String[] cmdDiv = command.split(" ");
				int id = Integer.parseInt(cmdDiv[2]);
				int index = getIndexById(id);
				if (index == -1) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}
				posts.remove(index);
				System.out.println(id + "번 게시물을 삭제했습니다.");
			} else {
				continue;
			}
			
			sc.close();
		}		
	}
	
	static void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		posts.add(new Post(1, GetTimeUtil.getNow(), "1번글 제목", "1번글 내용~@#$%"));
		posts.add(new Post(2, GetTimeUtil.getNow(), "2번글 제목", "2번글 내용~@#$%"));
		posts.add(new Post(3, GetTimeUtil.getNow(), "3번글 제목", "3번글 내용~@#$%"));
	}
	
	static int getIndexById(int id) {
		for (Post post : posts) {
			if (post.id == id) {
				return posts.indexOf(post);
			}
		}
		return -1;
	}
	
	static Post getPostById(int id) {
		int index = getIndexById(id);
		if (index != -1) {
			return posts.get(index);
		}
		return null;
	}
}

class Post {
	int id;
	String regDate;
	String title;
	String body;
	
	Post(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}

class GetTimeUtil {
	static String getNow() {
		LocalDateTime now = LocalDateTime.now();
		String nowDateTimeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return nowDateTimeStr;
	}
}