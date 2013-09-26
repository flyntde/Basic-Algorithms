package sorting;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;


class BinaryFileBuffer  {
        public static int BUFFERSIZE = 2048;
        public BufferedReader fbr;
        public File originalfile;
        private String cache;
        private boolean empty;
       
        public BinaryFileBuffer(File f, Charset cs, boolean usegzip) throws IOException {
                this.originalfile = f;
                InputStream in = new FileInputStream(f);
                if(usegzip) {
                        this.fbr = new BufferedReader(new InputStreamReader(new GZIPInputStream(in, BUFFERSIZE),cs));
                } else {
                        this.fbr = new BufferedReader(new InputStreamReader(in,cs));
                }
                reload();
        }
       
        public boolean empty() {
                return this.empty;
        }
       
        private void reload() throws IOException {
                try {
          if((this.cache = this.fbr.readLine()) == null){
            this.empty = true;
            this.cache = null;
          }
          else{
            this.empty = false;
          }
      } catch(EOFException oef) {
        this.empty = true;
        this.cache = null;
      }
        }
       
        public void close() throws IOException {
                this.fbr.close();
        }
       
       
        public String peek() {
                if(empty()) return null;
                return this.cache.toString();
        }
        public String pop() throws IOException {
          String answer = peek();
                reload();
          return answer;
        }      
}

