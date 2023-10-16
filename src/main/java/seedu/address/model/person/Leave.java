package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's leave.
 */
public class Leave {
    public static final int LEAVE_LENGTH = 12;
    public static final String MESSAGE_CONSTRAINTS = "Invalid leave format";
    public static final String NO_LEAVE = "000000000000";
    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public final String leave;

    /**
     * Constructs an empty {@code Leave}.
     */
    public Leave() {
        this.leave = NO_LEAVE;
    }

    /**
     * Constructs a {@code Leave}.
     *
     * @param leave A valid leave.
     */
    public Leave(String leave) {
        requireNonNull(leave);
        leave = leave.trim();
        checkArgument(isValidLeave(leave), MESSAGE_CONSTRAINTS);
        this.leave = leave;
    }

    /**
     * Returns true if a given string is a valid leave.
     */
    public static boolean isValidLeave(String test) {
        test = test.trim();
        if (test.length() != LEAVE_LENGTH) {
            return false;
        }
        for (int i = 0; i < LEAVE_LENGTH; i++) {
            if (test.charAt(i) != '0' && test.charAt(i) != '1') {
                return false;
            }
        }
        return true;
    }

    /**
     * Updates current leave.
     * @param change
     * @return
     */
    public Leave update(String change) {
        StringBuilder newLeave = new StringBuilder(leave);
        for (int i = 0; i < LEAVE_LENGTH; i++) {
            if (change.charAt(i) == '+') {
                newLeave.setCharAt(i, '1');
            }
            if (change.charAt(i) == '-') {
                newLeave.setCharAt(i, '0');
            }
        }
        return new Leave(newLeave.toString());
    }

    @Override
    public String toString() {
        if (leave.equals(NO_LEAVE)) {
            return "-";
        }
        String leaves = "";
        for (int i = 0; i < LEAVE_LENGTH; i++) {
            if (leave.charAt(i) == '1') {
                if (leaves.length() > 0) {
                    leaves += ", ";
                }
                leaves += MONTHS[i];
            }
        }
        return leaves;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherLeave = (Leave) other;
        return leave.equals(otherLeave.leave);
    }

    @Override
    public int hashCode() {
        return leave.hashCode();
    }

}