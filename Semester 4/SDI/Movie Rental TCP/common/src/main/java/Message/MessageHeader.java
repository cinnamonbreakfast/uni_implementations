package Message;

public enum MessageHeader
{
    ERROR,
    OK,

    CLIENT_ADD,
    CLIENT_GET_BY_ID,
    CLIENT_GET_ALL,
    CLIENT_GET_ALL_SORTBY,
    CLIENT_FILTER_FIRSTNAME,
    CLIENT_FILTER_LASTNAME,
    CLIENT_FILTER_AGE,
    CLIENT_SORT_ASC,
    CLIENT_SORT_DESC,
    CLIENT_REMOVE,
    CLIENT_UPDATE,

    MOVIE_ADD,
    MOVIE_GET_BY_ID,
    MOVIE_GET_ALL,
    MOVIE_GET_ALL_SORTBY,
    MOVIE_FILTER_NAME,
    MOVIE_FILTER_DESCRIPTION,
    MOVIE_FILTER_RATING,
    MOVIE_FILTER_PRICE,
    MOVIE_SORT_ASC,
    MOVIE_SORT_DESC,
    MOVIE_REMOVE,
    MOVIE_UPDATE,

    RENTAL_ADD,
    RENTAL_GET_ALL,
    RENTAL_REMOVE,
    RENTAL_MOST_RENTED,
    RENTAL_MOST_LOYAL_CUSTOMER,
    RENTAL_SORT_ASC,
    RENTAL_SORT_DESC,
    RENTAL_GET_BY_ID
}