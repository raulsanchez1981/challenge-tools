package challenge.filters;

import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by rsanchpa on 01/10/2017.
 */
//@Component
public class NoContentFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream ps = new PrintStream(baos);

        filterChain.doFilter(httpServletRequest,new HttpServletResponseWrapper(httpServletResponse) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), ps)
                );
            }
            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(new DelegatingServletOutputStream (new TeeOutputStream(super.getOutputStream(), ps))
                );
            }
        });

//        filterChain.doFilter(httpServletRequest, httpServletResponse);
        if (httpServletResponse.getContentType() == null || httpServletResponse.getContentType().equals("")) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }
        if (null != baos && baos.toString().equals("[]")) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }
    }
}