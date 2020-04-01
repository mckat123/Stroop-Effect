public class FileManager
{

   private String currentFileName;
   
   public FileManager()
         {  currentFileName = "mySubjectDataFile.txt";
         }
         
   public boolean createNewFile()
   { //stub
      return true;
   }
   
   public String[] retrieveFile()
   {   String [] dataStrings = { "jane doe"};
       
      //stub
      return dataStrings;
    }

    public boolean validFileName(String outfilename)
    {
        return true;
    }
}