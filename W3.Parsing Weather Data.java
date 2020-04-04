import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
//Maxing Temperature

public class CSVMax{
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar=null;
        for (CSVRecord currentRow:parser){
            largestSoFar=getLargestOfTwo(largestSoFar,currentRow);
        }return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord currentRow =hottestHourInFile(fr.getCSVParser());
            largestSoFar=getLargestOfTwo(largestSoFar,currentRow);
        }
        return largestSoFar;
    }    
    
    public CSVRecord getLargestOfTwo(CSVRecord largestSoFar,CSVRecord currentRow)
    {
        if (largestSoFar==null){
            largestSoFar=currentRow;
        }else{
            if (Double.parseDouble(currentRow.get("TemperatureF"))>Double.parseDouble(largestSoFar.get("TemperatureF")))
            {
                largestSoFar=currentRow;
            }
        }return largestSoFar;
    }
    public CSVRecord getColdestOfTwo(CSVRecord coldestSoFar,CSVRecord currentRow)
    {
        if (coldestSoFar==null){
            coldestSoFar=currentRow;
        }else{
            if (Double.parseDouble(currentRow.get("TemperatureF"))>Double.parseDouble(coldestSoFar.get("TemperatureF")))
            {
                coldestSoFar=currentRow;
            }
        }return coldestSoFar;
    }    
    public void testhottestInManyDays(){
        CSVRecord largest =hottestInManyDays();
        System.out.println("hottest temperature was" +largest.get("TemperatureF")
        +"at"+largest.get("DateUTC"));    
    } 
//Exercise Method 1
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar=null;
        for (CSVRecord currentRow:parser){
            System.out.println(currentRow.get("DateUTC")+","+currentRow.get("TemperatureF"));
            if (lowestSoFar==null){
                lowestSoFar=currentRow;
                
            }else{
                if (Double.parseDouble(currentRow.get("TemperatureF"))<Double.parseDouble(lowestSoFar.get("TemperatureF"))){
                lowestSoFar=currentRow;
                }
            } 
        }
        return lowestSoFar;
    }
    public void testcoldestHourInFile(){
        //FileResource fr=new FileResource();
        // CSVRecord lowest =coldestHourInFile(fr.getCSVParser());
        // System.out.println("Coldest temperature was" +lowest.get("TemperatureF")
        // +"at"+lowest.get("TimeEST"));  
    }    
    
//Exercise Method 2
    public String fileWithColdestTemperature()
    {
        DirectoryResource dr=new DirectoryResource();
        CSVRecord lowestSoFar=null;
        String fileName="";
        for (File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVRecord currentLowest=coldestHourInFile(fr.getCSVParser());
            lowestSoFar=getColdestOfTwo(lowestSoFar,currentLowest);
            fileName=f.getName();
                  }return fileName;
            }
    public void testFileWithColdestTemperature(){
        //String fileName=fileWithColdestTemperature();
        //System.out.println("Coldest day was in file weather-" +fileName);  
        //FileResource fr=new FileResource();
        //CSVRecord currentRow =coldestHourInFile(fr.getCSVParser());
        //System.out.println("Coldest temperature on that day was" +currentRow.get("TemperatureF"));
    }    
//Exercise Method 3
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar=null;
        for (CSVRecord currRow:parser){
            if (lowestSoFar==null){
                lowestSoFar=currRow;
            }
            
            if ( !currRow.get("Humidity").equals("N/A")){
            if(Integer.parseInt(currRow.get("Humidity"))<Integer.parseInt(lowestSoFar.get("Humidity")))
            {
               lowestSoFar= currRow;
            }
        }}return lowestSoFar;
    }     
    public void testLowestHumidityInFile(){
   // FileResource fr=new FileResource();
    //CSVRecord currentRow =lowestHumidityInFile(fr.getCSVParser());
    //System.out.println("Lowest Humidity was" +currentRow.get("Humidity")+" at " + currentRow.get("DateUTC"));
    }  
//Exercise Method 4
    public CSVRecord getlowerOfTwo(CSVRecord coldestSoFar,CSVRecord currentRow)
    {
        if (coldestSoFar==null){
            coldestSoFar=currentRow;
        }else{
            if (Double.parseDouble(currentRow.get("Humidity"))<Double.parseDouble(coldestSoFar.get("Humidity")))
            {
                coldestSoFar=currentRow;
            }
        }return coldestSoFar;
    }     
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord currentLowest= lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar=getlowerOfTwo(lowestSoFar,currentLowest);
        }return lowestSoFar;
    }    
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumi=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was"+ lowestHumi.get("Humidity")+" at "+
        lowestHumi.get("DateUTC"));
    }      
//Exercise Method 5    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumhumidity=0;
        int numbs=0;
        double avghumidity=0.0;
        for (CSVRecord currRow:parser)
        {            
            if ( !currRow.get("Humidity").equals("N/A")&&Double.parseDouble(currRow.get("Humidity"))>=value)
            {
            {
               sumhumidity= sumhumidity+Double.parseDouble(currRow.get("TemperatureF"));
               numbs+=1;
            }
        }}
        if (numbs==0){
            System.out.println("No temperatures with that humidity");
        }else{
        avghumidity=sumhumidity/numbs ;
        }return avghumidity;    
    } 
    
    public void testAverageTemperatureWithHighHumidityInFile(){
       FileResource fr=new FileResource();
       double avg =averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
       System.out.println("Average Temp when high Humidity" +avg);
 
    }     

    // public void testHoteestInDay(){
    // FileResource fr=new FileResource("data/2015/weather-2015-01-01.csv");
    // CSVRecord largest=hottestHourInFile(fr.getCSVParser());
    // System.out.println("hottest temperature was" +largest.get("TemperatureF")
    // +"at"+largest.get("TimeEST"));
    // }
}