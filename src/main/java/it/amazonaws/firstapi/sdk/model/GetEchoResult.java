/**
 * null
 */
package it.amazonaws.firstapi.sdk.model;

import java.io.Serializable;
import javax.annotation.Generated;

/**
 * 
 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/2e2aqdipfi-2019-09-06T12:11:39Z/GetEcho" target="_top">AWS API
 *      Documentation</a>
 */
@Generated("com.amazonaws:aws-java-sdk-code-generator")
public class GetEchoResult extends com.amazonaws.opensdk.BaseResult implements Serializable, Cloneable {

    private Empty empty;

    /**
     * @param empty
     */

    public void setEmpty(Empty empty) {
        this.empty = empty;
    }

    /**
     * @return
     */

    public Empty getEmpty() {
        return this.empty;
    }

    /**
     * @param empty
     * @return Returns a reference to this object so that method calls can be chained together.
     */

    public GetEchoResult empty(Empty empty) {
        setEmpty(empty);
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getEmpty() != null)
            sb.append("Empty: ").append(getEmpty());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof GetEchoResult == false)
            return false;
        GetEchoResult other = (GetEchoResult) obj;
        if (other.getEmpty() == null ^ this.getEmpty() == null)
            return false;
        if (other.getEmpty() != null && other.getEmpty().equals(this.getEmpty()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime * hashCode + ((getEmpty() == null) ? 0 : getEmpty().hashCode());
        return hashCode;
    }

    @Override
    public GetEchoResult clone() {
        try {
            return (GetEchoResult) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() " + "even though we're Cloneable!", e);
        }
    }

}
