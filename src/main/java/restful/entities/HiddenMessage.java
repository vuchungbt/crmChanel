package restful.entities;

import java.util.List;

/**
 *
 * developer VuChung
 */
public class HiddenMessage {

      private final String message;
      private final String result;
      private List data;
      private int status;

      public HiddenMessage(String message,String result) {
         this.message = message;
         this.result = result;
      }

    public HiddenMessage(String message, String result, List data, int status) {
        this.message = message;
        this.result = result;
        this.data = data;
        this.status = status;
    }
      

      public String getMessage() {
         return message;
      }
      public String getResult(){
          return result;
      }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
      
      
}