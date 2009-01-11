<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	
	<xsl:param name="bundleVersion" />
	
	<xsl:template match="/">
		<model bundleVersion="{$bundleVersion}">
			<xsl:apply-templates select="ExaltedCharacter/Statistics/Abilities/*"/>
		</model>
	</xsl:template>
	
	<xsl:template match="ExaltedCharacter/Statistics/Abilities/*[not(subTrait)]">
		<trait>
			<xsl:attribute name="id">
				<xsl:value-of select="name(.)"/>
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</trait>
	</xsl:template>	
	
	<xsl:template match="ExaltedCharacter/Statistics/Abilities/*[subTrait]">
		<trait>
			<xsl:attribute name="id">
				<xsl:value-of select="name(.)"/>
			</xsl:attribute>
			<xsl:attribute name="creationValue">
				<xsl:value-of select="subTrait[not(../subTrait/@creationValue &gt; ./@creationValue)]/@creationValue"/>
			</xsl:attribute>
			<xsl:attribute name="experiencedValue">
				<xsl:value-of select="subTrait[not(../subTrait/@experiencedValue &gt; ./@experiencedValue)]/@experiencedValue"/>   	
			</xsl:attribute>
			<xsl:apply-templates/>
		</trait>
	</xsl:template>
	
	
	<xsl:template match="*/subTrait">	
		<trait>
			<xsl:attribute name="id">
				<xsl:value-of select="./traitName/text()"/>
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
		</trait>
	</xsl:template>	
</xsl:stylesheet>