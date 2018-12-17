package cn.mtf.fileUtil;

import java.io.*;
import java.util.Date;

/**
 * Created by matengfei1 on 2018/11/23.
 */
public class FileSeperator {

    public static final long MB = 1024 * 1024;
    private File inFile;
    private String outPath;

    public FileSeperator(File in) {
        this.inFile = in;
        this.outPath = in.getParent();
    }

    public FileSeperator(File in, String out) throws Exception {
        this.inFile = in;
        this.outPath = out;
    }

    public void seperate(long size) {
        File outDirectory = new File(outPath);
        String fileName = inFile.getName();
        if (!outDirectory.exists()) {
            outDirectory.mkdir();
        }
        try {
            Reader reader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            String ucode = new Date().getTime() + "";
            for (int i = 0; ; i++) {
                File outFile = new File(outPath + File.separator + fileName + "-" + ucode + "-" + i);
                Writer writer = new FileWriter(outFile);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                long current = 0;
                while (current < size && (line = bufferedReader.readLine()) != null) {
                    current += line.getBytes().length;
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                writer.flush();
                bufferedWriter.close();
                writer.close();
                System.out.println("finished = " + (i + 1));
                if (null == line) {
                    break;
                }
            }
            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
