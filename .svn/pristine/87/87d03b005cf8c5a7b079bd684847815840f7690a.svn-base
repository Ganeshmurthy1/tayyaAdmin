package com.lintas.action;




import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.result.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class CustomStreamResult extends StrutsResultSupport {

     private static final long serialVersionUID = -1468409635999059851L;

    protected static final Log log = LogFactory.getLog(CustomStreamResult.class);

    public static final String DEFAULT_PARAM = "inputName";

    protected String contentType = "text/image";
    protected String contentLength;
    protected String contentDisposition = "inline";
    protected String inputName = "inputStream";
    protected InputStream inputStream;
    protected int bufferSize = 1024;

    public CustomStreamResult() {
        super();
    }

    public CustomStreamResult(InputStream in) {
        this.inputStream = in;
    }

    /**
     * @return Returns the bufferSize.
     */
    public int getBufferSize() {
        return (bufferSize);
    }

    /**
     * @param bufferSize The bufferSize to set.
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * @return Returns the contentType.
     */
    public String getContentType() {
        return (contentType);
    }

    /**
     * @param contentType The contentType to set.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return Returns the contentLength.
     */
    public String getContentLength() {
        return contentLength;
    }

    /**
     * @param contentLength The contentLength to set.
     */
    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    /**
     * @return Returns the Content-disposition header value.
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * @param contentDisposition the Content-disposition header value to use.
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * @return Returns the inputName.
     */
    public String getInputName() {
        return (inputName);
    }

    /**
     * @param inputName The inputName to set.
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * @see org.apache.struts2.dispatcher.StrutsResultSupport#doExecute(java.lang.String, com.opensymphony.xwork2.ActionInvocation)
     */
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

        OutputStream oOutput = null;

        try {
            if (inputStream == null) {
                // Find the inputstream from the invocation variable stack
                inputStream = (InputStream) invocation.getStack().findValue(conditionalParse(inputName, invocation));
            }

            if (inputStream == null) {
                String msg = ("Can not find a java.io.InputStream with the name [" + inputName + "] in the invocation stack. " +
                    "Check the <param name=\"inputName\"> tag specified for this action.");
                log.error(msg);
                throw new IllegalArgumentException(msg);
            }

            // Find the Response in context
            HttpServletResponse oResponse = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);

            Object customContentType = invocation.getStack().findValue(conditionalParse(contentType, invocation));
            if (customContentType != null) {
                contentType = (String) customContentType;
            }
            // Set the content type
            oResponse.setContentType(conditionalParse(contentType, invocation));

            // Set the content length
            if (contentLength != null) {
                String _contentLength = conditionalParse(contentLength, invocation);
                int _contentLengthAsInt = -1;
                try {
                    _contentLengthAsInt = Integer.parseInt(_contentLength);
                    if (_contentLengthAsInt >= 0) {
                        oResponse.setContentLength(_contentLengthAsInt);
                    }
                }
                catch(NumberFormatException e) {
                    log.warn("failed to recongnize "+_contentLength+" as a number, contentLength header will not be set", e);
                }
            }

            Object customContentDisposition = invocation.getStack().findValue(conditionalParse(contentDisposition, invocation));
            if (customContentDisposition != null) {
                contentDisposition = (String) customContentDisposition;
            }
            // Set the content-disposition
            if (contentDisposition != null) {
               // oResponse.addHeader("Content-disposition", conditionalParse(contentDisposition, invocation));
            }

            // Get the outputstream
            oOutput = oResponse.getOutputStream();

            if (log.isDebugEnabled()) {
                log.debug("Streaming result [" + inputName + "] type=[" + contentType + "] length=[" + contentLength +
                    "] content-disposition=[" + contentDisposition + "]");
            }

            // Copy input to output
            log.debug("Streaming to output buffer +++ START +++");
            byte[] oBuff = new byte[bufferSize];
            int iSize;
            while (-1 != (iSize = inputStream.read(oBuff))) {
                oOutput.write(oBuff, 0, iSize);
            }
            log.debug("Streaming to output buffer +++ END +++");

            // Flush
            oOutput.flush();
        }
        finally {
            if (inputStream != null) inputStream.close();
            if (oOutput != null) oOutput.close();
        }
    }
}
