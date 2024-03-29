<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes" cdata-section-elements="Name"/>
	
	<xsl:param name="bundleVersion" />
	
	<xsl:template match="Plot"/>
	
	<xsl:template match="/">
		<xsl:copy>
			<xsl:attribute name="bundleVersion">
				{$bundleVersion}
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>			
	</xsl:template>
	
	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>	
	</xsl:template>	
</xsl:stylesheet>		