package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Kevin
 * @Title: TestMain
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 9:24
 */
public class TestMain {
        public static void main(String[] args) throws Exception{
            File file = new File("C:\\Users\\smart\\IdeaProjects\\java_study\\src\\main\\java\\com\\chief\\station\\线路_站点_珠海.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            Set<String> set = new HashSet<String>();
            while ((str = br.readLine()) != null){
                String[] split = str.split("\\|\\|");
                if(split[0] == null || "".equals(split[0])){
                    continue;
                }
                set.add(split[0]);
            }

            final List<String> arrs = new ArrayList<String>();
            set.forEach(e->{
                arrs.add(e);
            });

            arrs.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

            System.out.println(arrs);
        }
}
