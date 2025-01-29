package tool;

public class FileRename {
	public static String fileRename(String inputPath) {
		String currentDirectory=System.getProperty("user.dir");
		
		System.out.println(currentDirectory);
		
		String result=currentDirectory.replace("eclipse","workspace");
		String finalset=result+inputPath;
		finalset=finalset.replace("\\", "/");
		finalset=finalset.replace("work","workspace");
		return finalset;
	}
}