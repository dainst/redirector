package org.dainst.redirector;

/**
 * @author Daniel M. de Oliveira
 */
public class Constants {

    // A library for getting the status codes is not used on purpose
    // to protect against dependency change.
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_OK = 200;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_BAD_REQUEST = 400;

    public static final String HEADER_AUTH = "Authorization";
    public static final String HEADER_CT = "Content-Type";
    public static final String HEADER_LOC = "location";
    public static final String HEADER_JSON = "application/json";
}
