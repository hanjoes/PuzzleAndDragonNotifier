package us.bibos.puzzleanddragonnotifier.DBContract.Exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String detailMessage) {
        super(detailMessage);
    }
}
