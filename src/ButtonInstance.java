import javax.swing.*;

public class ButtonInstance extends JButton
{
	private int value;
	private int index;
	
	
	ButtonInstance()
	{}
	
	ButtonInstance(String nameAndValue)
	{
		setText(nameAndValue);
		value = Integer.parseInt(nameAndValue);
		
	}
	
	ButtonInstance(int nameAndValue)
	{
		setText(String.valueOf(nameAndValue));
		value = nameAndValue;
		
	}
	
	
	ButtonInstance(int nameAndValue, int index)
	{
		setText(String.valueOf(nameAndValue));
		value = nameAndValue;
		this.index = index;
		
	}
	
	ButtonInstance(String nameAndValue, int index)
	{
		setText(nameAndValue);
		value = Integer.parseInt(nameAndValue);
		this.index = index;
		
	}
	
	
	
	public void setNameAndValue(String nameAndValue)
	{
		setText(nameAndValue);
		value = Integer.parseInt(nameAndValue);
		
	}
	
	public void setValueAndName(String valueAndName)
	{
		setText(valueAndName);
		value = Integer.parseInt(valueAndName);
		
	}
	
	public void setNameAndValue()
	{
		setText(null);
		value = 0;
		
	}
	
	public void setValueAndName()
	{
		value = 0;
		setText(null);
		
	}
	
	public void setValueAndName(int valueAndName)
	{
		setText(String.valueOf(valueAndName));
		value = valueAndName;
		
	}
	
	public void setNameAndValue(int nameAndValue)
	{
		setText(String.valueOf(nameAndValue));
		value = nameAndValue;
		
	}
	
	public void setIndex(int index)
	{
		this.index = index;
		
	}
	
	
	
	public void setIndexAndValueAndName(int index)
	{
		this.index = index;
		value = 0;
		setText(null);
		
	}
	
	public void setIndexAndNameAndValue(int index)
	{
		this.index = index;
		setText(null);
		value = 0;
		
	}
	
	public void setNameAndValueAndIndex(String nameAndValue, int index)
	{
		setText(nameAndValue);
		value = Integer.parseInt(nameAndValue);
		this.index = index;
		
	}
	
	public void setNameAndValueAndIndex(int nameAndValue, int index)
	{
		setText(String.valueOf(nameAndValue));
		value = nameAndValue;
		this.index = index;
		
	}
	
	public void setValueAndNameAndIndex(int ValueAndName, int index)
	{
		setText(String.valueOf(ValueAndName));
		value = ValueAndName;
		this.index = index;
		
	}
	
	public void setValueAndNameAndIndex(String valueAndName, int index)
	{
		setText(valueAndName);
		value = Integer.parseInt(valueAndName);
		this.index = index;
		
	}
	
	public void setIndexAndNameAndValue(int index, String nameAndValue)
	{
		this.index = index;
		setText(nameAndValue);
		value = Integer.parseInt(nameAndValue);
		
	}
	
	public void setIndexAndNameAndValue(int index, int nameAndValue)
	{
		this.index = index;
		setText(String.valueOf(nameAndValue));
		value = nameAndValue;
		
	}
	
	public void setIndexAndValueAndName(int index, String valueAndName)
	{
		this.index = index;
		setText(valueAndName);
		value = Integer.parseInt(valueAndName);
		
	}
	
	public void setIndexAndValueAndName(int index, int valueAndName)
	{
		this.index = index;
		setText(String.valueOf(valueAndName));
		value = valueAndName;
		
	}
	
	
	
	public int getValue()
	{
		return value;
		
	}
	
	public String getName()
	{
		return (getText());
		
	}
	
	public int getIndex()
	{
		return index;
		
	}
	
	
	public String getNameAndValue()
	{
		return (getText() + ", " + value);
		
	}
	
	public String getValueAndName()
	{
		return (value + ", " + getText());
		
	}
	
	public String getValueAndIndex()
	{
		return (value + ", " + index);
		
	}
	
	public String getIndexAndValue()
	{
		return (index + ", " + value);
		
	}
	
	public String getIndexAndName()
	{
		return (index + ", " + getText());
		
	}
	
	public String getNameAndIndex()
	{
		return (getText() + ", " + index );
		
	}
	
	
	public String getValueAndNameAndIndex()
	{
		return (value + ", " + getText() + ", " + index);
		
	}
	
	public String getNameAndValueAndIndex()
	{
		return (getText() + ", " + value +  ", " + index);
		
	}
	
	public String getIndexAndValueAndName()
	{
		return (index + ", " + value +  ", " + getText());
		
	}
	
	public String getValueAndIndexAndName()
	{
		return (value + ", " + index + ", " + getText());
		
	}
	
	public String getNameAndIndexAndValue()
	{
		return (getText() + ", "  + index +  ", " + value);
		
	}
	
	public String getIndexAndNameAndValue()
	{
		return (index + ", " + getText() + ", " + value);
		
	}
	
}