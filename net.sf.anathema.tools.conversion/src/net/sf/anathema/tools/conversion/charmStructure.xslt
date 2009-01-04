<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	<xsl:template match="/">
		<charmlist>
     		<xsl:apply-templates select="@* | node() " />
	    </charmlist>
	</xsl:template>
	
	<xsl:template match="charm">
		<xsl:copy>
    		<xsl:copy-of select="@*"/>
		    <prerequisite><xsl:apply-templates select="prerequisite/node()" /></prerequisite>
	    </xsl:copy>
	</xsl:template>
	
	<xsl:template match="prerequisite/charmReference">
		<xsl:copy>
     		<xsl:apply-templates select="node() " />
    		<xsl:copy-of select="@*"/>
	    </xsl:copy>
	</xsl:template>
	
	<xsl:template match="charmAttributeRequirement[starts-with(@attribute,'Excellency')]">
		<xsl:variable name="type" select="translate(../../@exalt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')">
		</xsl:variable>
	    <charmReference>
			<xsl:attribute name="id"><xsl:value-of select="$type"/>.any.{0}.excellency</xsl:attribute>
	    </charmReference>
	</xsl:template>
</xsl:stylesheet>