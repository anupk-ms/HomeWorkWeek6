package anupk;

public class MyFolderProxy implements MyFolder{

	 MyFolder folder;
	 User user;
	  
	 public MyFolderProxy(User user) {
	  this.user = user;
	 }

	 public void performOperations() {
	  
		 if(user.getUserName().equalsIgnoreCase("anupk") && 
				 user.getPassword().equalsIgnoreCase("kumar"))
		 {
			 folder=new SystemFolder();
			 folder.performOperations();
		 }
		 else
		 {
			 System.out.println("You don't have access to this folder");
		 }
	 }
}
