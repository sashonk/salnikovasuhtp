package salnikova.web;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;





public class WebHelper {

    /**
     * processes <b>multipart/form-data</b> encoded data
     * 
     */
    public static Map<String, Object> processRequest(
            final HttpServletRequest request,
            final List<String> allowedContentTypes, final int maxFileSize,
            final List<String> messages) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return result;
        }

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload();



        // Parse the request
        FileItemIterator iter = upload.getItemIterator(request);
        while (iter.hasNext()) {
            FileItemStream item = iter.next();

            InputStream is = item.openStream();
            if (item.isFormField()) {

                byte[] data = IOUtils.toByteArray(item.openStream());
                result.put(item.getFieldName(), new String(data, "utf-8"));

            } else {
                if (item.getName().equals("")) { //$NON-NLS-1$
                    String msg =
 "Файл не найден";
                    messages.add(msg);
                    continue;
                }

                boolean contentTypeAllowed = false;
                if (allowedContentTypes == null) {
                    contentTypeAllowed = true;
                } else {
                    for (String contentType : allowedContentTypes) {
                        if (item.getContentType().equals(contentType)) {
                            contentTypeAllowed = true;
                            break;
                        }
                    }
                }

                if (!contentTypeAllowed) {     
					String msg = String.format(
							"Неверный тип %s файла %s. Допустимые форматы: %s",
							item.getContentType(), item.getName(),
                         Arrays.toString(allowedContentTypes.toArray()));
                    messages.add(msg);
                    continue;
                }

                // Process the input stream
                int read = 0;
                int size = 8 * 1024;
                byte[] b = new byte[size];

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((read = is.read(b)) > 0) {
                    baos.write(b, 0, read);
                }

                if ((maxFileSize > 0) && (baos.size() > (maxFileSize))) {
                    String msg =
                        String.format(
							"Ошибка! Размер файла %s превышает %d байт", //$NON-NLS-1$
                            item.getName(), Integer.valueOf(maxFileSize));
                    messages.add(msg);
                    continue;
                }

				Upload doc = new Upload();
                doc.setContent(baos.toByteArray());
                doc.setName(item.getName());
                result.put(item.getFieldName(), doc);

            }

        }

        return result;

    }

	
}
