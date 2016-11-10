package util;

import android.util.Log;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by JoséAntonio on 09/03/2016.
 */
public class SoapSerEnv extends SoapSerializationEnvelope {

    public SoapSerEnv(int version) {
        super(version);
        this.implicitTypes = true;
        this.dotNet = true;
        this.addAdornments = false;
    }

    public void write(XmlSerializer writer)
            throws IOException

    {
        Log.d("SOAP_SERV_ENV", "Serialzation Envelope.");
        writer.setPrefix("i", xsi);
        writer.setPrefix("d", xsd);
        writer.setPrefix("c", enc);
        writer.setPrefix("SoapEnv", env);
        writer.startTag(env, "Envelope");
        writer.startTag(env, "Header");
        writeHeader(writer);
        writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        writeBody(writer);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }
}
