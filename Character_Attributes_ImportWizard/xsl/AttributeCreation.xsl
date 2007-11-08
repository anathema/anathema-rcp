<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	
	<xsl:param name="bundleVersion" />
	
	<xsl:template match="/">
		<model bundleVersion="{$bundleVersion}">
			<xsl:apply-templates select="ExaltedCharacter/Statistics/Attributes/*/*"/>
		</model>
	</xsl:template>
	
	<xsl:template match="ExaltedCharacter/Statistics/Attributes/*/*">	  	  	
		<trait>
			<xsl:attribute name="id">
				<xsl:value-of select="name(.)"/>
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
		</trait>
	</xsl:template>	
</xsl:stylesheet>