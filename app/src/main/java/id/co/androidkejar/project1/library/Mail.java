package id.co.androidkejar.project1.library;

import android.content.Intent;
import android.net.Uri;
/**
 * Created by imammagribi on 5/7/17.
 */

public class Mail {
    /**
     * this for send email
     *
     * @author Imammagribi <imammagribi@gmail.com>
     */
    public Intent sendMail(String[] to, String text) {

        Intent mail = new Intent(Intent.ACTION_SEND);
        mail.setData(Uri.parse("mailto:"));
        mail.setType("text/plain");
        mail.putExtra(Intent.EXTRA_EMAIL, to);
        mail.putExtra(Intent.EXTRA_SUBJECT, "Order");
        mail.putExtra(Intent.EXTRA_TEXT, text);
       return mail;
    }
}
