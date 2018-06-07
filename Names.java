import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Names
{
    private List<String> name;

	@XmlElement
    public List<String> getName ()
    {
        return name;
    }

    public void setName (List<String> name)
    {
        this.name = name;
    }
}