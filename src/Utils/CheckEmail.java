package Utils;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class CheckEmail {

    public static int hear(BufferedReader in) throws IOException {
        String line = null;
        int res = 0;

        while ((line = in.readLine()) != null) {
            System.out.println("Head line = " + line);
            String pfx = line.substring(0, 3);
            try {
                res = Integer.parseInt(pfx);
            } catch (Exception ex) {
                res = -1;
            }
            if (line.charAt(3) != '-') break;
        }
        return res;
    }

    public static void say(BufferedWriter wr, String text) throws IOException {
        wr.write(text + "\r\n");
        wr.flush();

    }

    public static ArrayList getMX(String hostName) throws NamingException {

        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext(env);
        Attributes attrs = ictx.getAttributes(hostName, new String[]{"MX"});
        Attribute attr = attrs.get("MX");

        if ((attr == null) || (attr.size() == 0)) {
            attrs = ictx.getAttributes(hostName, new String[]{"A"});
            attr = attrs.get("A");
            if (attr == null)
                throw new NamingException("No match for name '" + hostName + "'");
        }

        ArrayList res = new ArrayList();
        NamingEnumeration en = attr.getAll();

        while (en.hasMore()) {
            String mailhost;
            String x = (String) en.next();
            String f[] = x.split(" ");

            if (f.length == 1) {
                mailhost = f[0];
            } else if (f[1].endsWith(".")) {
                mailhost = f[1].substring(0, (f[1].length() - 1));
            } else {
                mailhost = f[1];
            }
            res.add(mailhost);
        }
        return res;
    }

    public static boolean isAddressValid(String address) {
        int pos = address.indexOf('@');
        if (pos == -1) return false;

        String domain = address.substring(++pos);
        ArrayList mxList = null;
        try {
            mxList = getMX(domain);
        } catch (NamingException ex) {
            return false;
        }

        if (mxList.size() == 0) return false;

        for (int mx = 0; mx < mxList.size(); mx++) {
            boolean valid = false;
            try {
                int res = 0;

                Socket skt = new Socket((String) mxList.get(mx), 25);
                BufferedReader rdr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                BufferedWriter wtr = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));

                res = hear(rdr);
                if (res != 220) throw new Exception("Invalid header");

                say(wtr, "EHLO peppeb176");
                res = hear(rdr);
                if (res != 250) throw new Exception("Not ESMTP");

                say(wtr, "MAIL FROM: <info@peppeb176>");
                res = hear(rdr);
                if (res != 250) throw new Exception("Sender rejected");

                say(wtr, "RCPT TO: <" + address + ">");
                res = hear(rdr);

                say(wtr, "RSET");
                hear(rdr);

                say(wtr, "QUIT");
                hear(rdr);
                if (res != 250) throw new Exception("Address is not valid!");

                valid = true;
                rdr.close();
                wtr.close();
                skt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (valid) return true;
            }
        }
        return false;
    }

    /*public static void main(String[] args) {
        System.out.println("peppeb176@icloud.com.com" + "is valid? " +
                isAddressValid("peppeb176@icloud.com.com"));
    }*/
}
