public class SyncDomain {
	
	private static String name;
	
	private static Integer age;
	
	private static int count;
	
	private static int agec;
	
	private static SyncDomain s=new SyncDomain();
	

	public static SyncDomain getInstance(int a,int b) {
		count=a;
		agec=b;
		return s;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	private SyncDomain() {
		super();
	}
	public SyncDomain(int a, int b) {
		count=a;
		agec=b;
	}
	public synchronized void Sync(){
		/*synchronized (this) {
			name=++count+"";
			age=++agec;
			System.out.println("name:"+name+"\t age:"+age);
		}*/
		name=++count+"";
		age=++agec;
		System.out.println("name:"+name+"\t age:"+age);
	}
	

}
