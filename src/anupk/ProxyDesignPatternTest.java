package anupk;

public class ProxyDesignPatternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
		// If we give correct userName and password
		User user=new User("anupk","kumar");
		MyFolderProxy folderProxy=new MyFolderProxy(user);
		System.out.println("When userName and password are correct:");
		folderProxy.performOperations();
		System.out.println("**************************************");
		
		// if we give wrong userName and Password
		User userWrong=new User("abc","abc");
		MyFolderProxy folderProxyWrong=new MyFolderProxy(userWrong);
		System.out.println("When userName and password are incorrect:");
		folderProxyWrong.performOperations();
	}
}
