<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	<xsl:preserve-space elements="*"/>
	<xsl:template match="ExaltedCharacter/Statistics/Attributes">
	  <model>
		<xsl:for-each select="*">
		  <xsl:call-template name="convert"/>
		</xsl:for-each>
	  </model>
	</xsl:template>
	
	<xsl:template name="convert">
		<xsl:for-each select="*">
		   <trait>
			<xsl:attribute name="id">
			  <xsl:value-of select="name(.)"/>
			</xsl:attribute>
			<xsl:for-each select="@*">
		      <xsl:copy/>
			</xsl:for-each>
		  </trait>
	  </xsl:for-each>
	</xsl:template>
</xsl:stylesheet>