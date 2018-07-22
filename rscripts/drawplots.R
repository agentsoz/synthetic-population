DrawBarSA2Plot <-
  function(dist1,
           dist2,
           out_file,
           plot_main_title,
           plot_xlab,
           plot_ylab,
           xlabels,
           legend,
           sa2) {
    CreateDir(dirname(out_file))
    pdf(out_file)
    counts <- matrix(c(dist1, dist2), ncol = 2)
    dplot <- barplot(
      t(counts),
      main = plot_main_title,
      col = c("blue", "red"),
      beside = TRUE,
      border = NA,
      las = 1,
      cex.lab = 0.8,
      cex.axis = 0.8,
      cex.main = .9,
      cex.sub = .8,
      xaxs = "i",
      cex.names = 0.8,
      xaxt = "n",
      names.arg = xlabels
    )
    
    box(bty = "l")
    mtext(
      side = 1,
      at = colMeans(dplot),
      line = 0.1,
      text = rownames(counts),
      cex = 0.4,
      las = 1
    )
    legend(
      "topright",
      fill = c("blue", "red"),
      legend = legend,
      bty = "n",
      cex = 0.8,
      border = NA
    )
    title(xlab = plot_xlab,
          line = 1.5,
          cex.lab = .8)
    title(ylab = plot_ylab,
          line = 2.5,
          cex.lab = .8)
    mtext(paste(sa2, "SA2"), cex = 0.6)
    axis(1, at = dplot[seq(1,length(dplot),2)], labels = xlabels, tcl = -0.25)
    dev.off()
  }

DrawErrorFrequencyPlot <- function(dist1, dist2,plot_main_title){
  counts <- matrix(c(dist1, dist2), ncol = 2)
  dplot <- barplot(
    t(counts),
    main = plot_main_title,
    col = c("gary", "black"),
    beside = TRUE,
    border = NA,
    las = 1,
    cex.lab = 0.8,
    cex.axis = 0.8,
    cex.main = .9,
    cex.sub = .8,
    xaxs = "i",
    cex.names = 0.8,
    xaxt = "n"
  )
}

DrawQQPlot <- function(census_dist, synth_dist, out_file,
                       plot_main_title,
                       plot_xlab,
                       plot_ylab,
                       xlabels,
                       legend,
                       sa2){
  library(ggplot2)
  
  pdf(out_file)
  theme_set(theme_bw()+theme(legend.position="none",text = element_text(size = 20),plot.title = element_text(hjust = -1), axis.text.x = element_text(hjust=0.7)))
  
  p1 <- qplot(census_dist, synth_dist, xlab=plot_xlab, ylab=plot_ylab, main = plot_main_title, color="am")
  p1 <- p1 + geom_point(position=position_jitter(h=0.1, w=0.1),
                        shape = 21, alpha = 0.5, size = 3)+  geom_abline(aes(colour='A', slope = 1, intercept=0))
  print(p1)
  dev.off()
}
