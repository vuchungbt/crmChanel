package restful.entities;

/**
 *
 * developer VuChung
 */
public class HiddenMessage {

      private final String message;
      private final String result;

      public HiddenMessage(String message,String result) {
         this.message = message;
         this.result = result;
      }

      public String getMessage() {
         return message;
      }
      public String getResult(){
          return result;
      }
}